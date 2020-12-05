import React, {Component } from 'react';
import {Button, ModalBody, ModalFooter, ModalHeader, Input, Table} from "reactstrap";
import TextField from "@material-ui/core/TextField";
import Chessground from "react-chessground";
import "react-chessground/dist/styles/chessground.css"
import {sendPostRequest} from "../components/API";
const { Chess } = require('./chess.js')
import Modal from "@material-ui/core/Modal";

export default class Chessboard extends Component {
    constructor(props) {
        super(props);

        this.togglePopup = this.togglePopup.bind(this);
        this.onMove = this.onMove.bind(this);

        this.state = {
            chess : new Chess(),
            pendingMove: [],
            selectVisible: false,
            fen : "",
            lastMove: []
        }

    }

    onMove(from, to) {
        sendPostRequest('move', {'from': from, 'to': to, 'match': 1})
            .then(r => {
                let validLogin = r.data.valid;
                if (!validLogin) props.produceSnackBar("Invalid Move", "error");
            })
    }

    render() {
        return (
            <Modal open={thisMe.props.popupOpen} onClose={this.togglePopup}>
            <div>
                <Chessground
                    width="30vw"
                    height="30vw"
                    fen={this.props.fen}
                    onMove={this.onMove}
                />
            </div>
            </Modal>
        )
    }

    togglePopup() {
        this.props.togglePopup();
    }
}

