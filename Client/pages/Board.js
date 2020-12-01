import React, { Component } from 'react';

import Piece from './Piece';
import Square from './Square';
import Notation from './Notation';
import PhantomPiece from './PhantomPiece';
import Row from './Row';
import Chessboard from 'chessboardjsx'
import {sendPostRequest} from "../components/API";

class Board extends Component {

    constructor(props) {
        super(props);

        this.state = {
            from : '',
            to : ''
        }
        //sendPostRequest("move", {"from" : this.state.from, "to": this.state.to, "match" : "1"}).then(r=>console.log(r.data))
    }


    setSquareCoordinates(x, y, square) {
        this.setState({[square]: {x, y}});
    }

    getSquareCoordinates(sourceSquare, targetSquare) {
        this.setState({from: sourceSquare, to: targetSquare})
        return(
            {
                sourceSquare: this.state[sourceSquare],
                targetSquare: this.state[targetSquare]
            })
    }

    showPhantom({ square, targetSquare, phantomPiece }) {
        const isActivePiece = (square, targetSquare) =>
            targetSquare && targetSquare === square;

        return (
            phantomPiece &&
            phantomPiece[targetSquare] &&
            isActivePiece(square, targetSquare)
        );
    };

    hasPiece(currentPosition, square) {
        return currentPosition &&
            Object.keys(currentPosition) &&
            Object.keys(currentPosition).includes(square);
    }


    render() {
        return (
            <Chessboard position='start'>
            </Chessboard>
        );
    }
}


export default Board;