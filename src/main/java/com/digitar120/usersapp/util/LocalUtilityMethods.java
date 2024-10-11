package com.digitar120.usersapp.util;

import com.digitar120.usersapp.exception.globalhandler.BadRequestException;
import com.digitar120.usersapp.exception.globalhandler.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.*;

public final class LocalUtilityMethods {// https://stackoverflow.com/questions/25223553/how-can-i-create-an-utility-class

    private LocalUtilityMethods(){}

    // Verificar que un elemento existe en un repositorio.

    // T -> El tipo de objeto que almacena y que devuelve el repositorio (por ejemplo, User, Cart, Item, Product)
    // S -> Representa a la interface de repositorio que extiende a JPARepository (CartRepository, ProductRepository, etc)
    // U -> Tipo de Id del repositorio (Integer, Long, etc.)
    public static <T,S extends JpaRepository<T,U>, U> void verifyElementExists(S repository, U id, String exceptionMessage, HttpStatus httpStatusCode){

        Optional<T> optionalElement = repository.findById(id);

        if(optionalElement.isEmpty() && httpStatusCode.equals(HttpStatus.NOT_FOUND)) {

            throw new NotFoundException(exceptionMessage);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------


    // Verificar que un elemento existe y devolverlo

    public static <T,S extends JpaRepository<T,U>, U> T verifyElementExistsAndReturn(S repository, U id, String exceptionMessage, HttpStatus httpStatusCode){
        Optional<T> optionalElement = repository.findById(id);

        if(optionalElement.isEmpty()) {

            switch(httpStatusCode){
                case NOT_FOUND:
                    throw new NotFoundException(exceptionMessage);

                case BAD_REQUEST:
                    throw new BadRequestException(exceptionMessage);
            }
        }

        return optionalElement.get();
    }

    // ---------------------------------------------------------------------------------------------------------------

    // Verificar que un elemento NO existe.
    public static <T,S extends JpaRepository<T,U>, U> void verifyElementNotExists(S repository, U id, String exceptionMessage, HttpStatus httpStatusCode){
        Optional<T> optionalElement = repository.findById(id);

        if(optionalElement.isPresent() && httpStatusCode.equals(HttpStatus.BAD_REQUEST)) {
            throw new BadRequestException(exceptionMessage);
        }
    }

    public static void quickTest() {
        List<String> lines = Arrays.asList(
                "I AM A DWARF AND I'M DIGGING A HOLE, DIGGY DIGGY HOLE, DIGGY DIGGY HOOOOLE",
                "SCOTLAND IS NOT A REAL COUNTRY. YOU'RE AN ENGLISHMAN IN A DRESS",
                "THIS. IS A BUCKET. -- DEAR GOD... -- THERE'S MORE. --- NO...",
                "I DON'T WANT YOUR DAMN LEMONS, WHAT AM I SUPPOSED TO DO WITH THESE?"
        );

        Random random = new Random();

        System.out.println(random.nextInt(lines.size())+1);
    }
}
