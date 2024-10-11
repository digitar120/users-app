package com.digitar120.usersapp.service;

import com.digitar120.usersapp.exception.globalhandler.NotFoundException;
import com.digitar120.usersapp.mapper.NewUserDTOToUser;
import com.digitar120.usersapp.persistence.entity.User;
import com.digitar120.usersapp.persistence.repository.UserRepository;
import com.digitar120.usersapp.service.dto.EditUserDTO;
import com.digitar120.usersapp.service.dto.NewUserDTO;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    private final Integer ID_USER_1 = 1;
    private final User USER_1 = new User(ID_USER_1, "Digitar", "120");

    @Mock UserRepository repo;

    @InjectMocks UserService service;

    @Mock NewUserDTOToUser mapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private void findByIdReturnsUser() {
        when(repo.findById(ID_USER_1)).thenReturn(Optional.of(USER_1));
    }

    private void findByIdReturnsNothing() {
        when(repo.findById(ID_USER_1)).thenReturn(Optional.empty());
    }

    private void repoSaveReturnsSameUser() {
        Mockito.doAnswer(AdditionalAnswers.returnsFirstArg())
                .when(repo).save(any(User.class));
    }

    @Test
    @DisplayName("listAllUsers llama para buscar")
    public void test_when_listAllUsers_then_repoSearchCallMade(){
        when(repo.findAll()).thenReturn(List.of(USER_1));

        service.listAllUsers();

        verify(repo, times(1)).findAll();
    }

    @Test
    @DisplayName("listAllUsers no devuelve lista vacía")
    public void test_when_listAllUsers_then_returnedListNotEmpty(){
        when(repo.findAll()).thenReturn(List.of(USER_1));

        assertFalse(service.listAllUsers().isEmpty());
    }

    @Test
    @DisplayName("findById ejecuta búsqueda")
    public void test_when_findById_then_searchCallMade(){
        findByIdReturnsUser();

        service.findById(ID_USER_1);

        verify(repo, times(1)).findById(ID_USER_1);
    }

    @Test
    @DisplayName("findById devuelve objeto correcto")
    public void test_when_findById_then_correctObjectReturned(){
        findByIdReturnsUser();

        assertEquals(USER_1, service.findById(ID_USER_1));
    }

    @Test
    @DisplayName("newUser ejecuta búsqueda, mapeo y guardado")
    public void test_when_newUser_then_searchMapAndSaveCallsMade(){
        NewUserDTO userDTO = new NewUserDTO(2, "Linus", "Torvalds");

        findByIdReturnsNothing();
        repoSaveReturnsSameUser();
        when(mapper.map(any())).thenReturn(new User());

        service.newUser(userDTO);

        verify(repo, times(1)).findById(2);
        verify(mapper, times(1)).map(userDTO);
        verify(repo, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("newUser devuelve objecto correcto")
    public void test_when_newUser_then_correctObjectReturned(){
        NewUserDTO userDTO = new NewUserDTO(2, "Linus", "Torvalds");
        User newUser = new User(2, "Linus", "Torvalds");

        findByIdReturnsNothing();
        repoSaveReturnsSameUser();

        when(mapper.map(any())).thenReturn(newUser);

        assertEquals(newUser, service.newUser(userDTO));
    }

    @Test
    @DisplayName("editUser ejecuta búsqueda y guardado")
    public void test_when_editUser_then_searchAndSaveCallsMade(){
        EditUserDTO userDTO = new EditUserDTO("Carl", "Johnson");

        findByIdReturnsUser();
        repoSaveReturnsSameUser();

        service.editUser(1, userDTO);

        verify(repo, times(1)).findById(ID_USER_1);
        verify(repo, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("editUser arroja excepción correctamente")
    public void test_when_editUser_and_userNotFound_then_exceptionThrown(){
        findByIdReturnsNothing();

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("No se encontró el usuario.");

        service.editUser(1, new EditUserDTO());
    }

    @Test
    @DisplayName("editUser devuelve elemento correctamente")
    public void test_when_editUser_then_returnCoorectObject(){
        EditUserDTO userDTO = new EditUserDTO("Carl", "Johnson");

        findByIdReturnsUser();
        repoSaveReturnsSameUser();

        assertTrue(
                EqualsBuilder.reflectionEquals(
                        new User(1, "Carl", "Johnson"),
                        service.editUser(1, userDTO)));
    }

    @Test
    @DisplayName("deleteUser ejecuta búsqueda y borrado")
    public void test_when_deleteUser_then_searchAndDeletionCallsMade(){
        findByIdReturnsUser();

        service.deleteUser(1);

        verify(repo, times(1)).findById(1);
        verify(repo, times(1)).deleteById(1);
    }
}
