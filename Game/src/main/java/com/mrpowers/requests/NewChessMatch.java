package com.mrpowers.requests;

import com.mrpowers.exceptions.IllegalMoveException;

public class NewChessMatch extends RequestData{
    private String user1;
    private String user2;

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {

    }
}
