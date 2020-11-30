import React, { Component } from 'react';

import Piece from './Piece';
import Square from './Square';
import Notation from './Notation';
import PhantomPiece from './PhantomPiece';
import Row from './Row';
import Chessboard from 'chessboardjsx'

class Board extends Component {
    setSquareCoordinates(x, y, square) {
        this.setState({[square]: {x, y}});
    }

    getSquareCoordinates(sourceSquare, targetSquare) {
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
            <Chessboard  position="start" onDrop={() => {console.log("heyyy")}}>
            </Chessboard>
        );
    }
}

export default Board;