import React, {Component } from 'react';
import {Button, Modal, ModalBody, ModalFooter, Table} from "reactstrap";
import Col from "reactstrap/es/Col";
import Chessground from "react-chessground";

export default class Match extends Component {
    constructor(props){
        super(props);

        this.defineName = this.defineName.bind(this);
        this.togglePopup = this.togglePopup.bind(this);

        this.state = {
            placeName: " ",
            chessBoardPopup: false
        }
    }

    render() {
        return (
            <div>
                <Modal isOpen={this.props.popupOpen} centered={true} toggle={this.togglePopup}>
                    <ModalBody>
                        {this.defineName()}
                    </ModalBody>
                    <ModalFooter>
                        <Button color="secondary" onClick={this.togglePopup}>Close</Button>
                    </ModalFooter>
                </Modal>
            </div>
        )
    }

    defineRow(username) {
        return (
            <tr>
                <td>{username}</td>
                <td>
                    <Button onClick={this.toggleChessPopup} color="primary">Play Game</Button>
                </td>
            </tr>
        )

    }

    defineName() {
        return (
            <Table striped responsive>
                <thead>
                <tr>
                    <th>Opponent</th>
                    <th>Game</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    {this.defineRow('mmihevc')}
                </tbody>
            </Table>
        )
    }
    //Use to render table once we have info to populate
    renderGames() {
        return (
            <tr>
                <td>Opponent</td>
                <td>
                    <Button onClick={() => {this.toggleChessPopup();this.togglePopup();}} style={{backgroundColor: 'green'}}>✓</Button>
                    <Button onClick={this.toggleChessPopup} style={{backgroundColor: 'red'}}>X</Button>
                </td>
            </tr>
        )
    }

    togglePopup() {
        this.props.togglePopup();
    }

    renderChessBoard() {
        return (
                <Col xs={9}>
                    {this.state.chessboardPopup ?
                        <Chessground
                            width="38vw"
                            height="38vw"
                            fen={'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR'}
                        /> : null }
                </Col>
            )

       // <Chessboard popupOpen={this.state.chessBoardPopup} togglePopup={this.toggleChessPopup}/>
    }

    toggleChessPopup() {
        this.setState({chessBoardPopup : !this.state.chessBoardPopup});
    }

}