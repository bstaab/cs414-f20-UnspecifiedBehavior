import React, {Component } from 'react';
import {Button, Modal, ModalBody, ModalFooter, Table} from "reactstrap";
import TextField from "@material-ui/core/TextField";

export default class CreateMatch extends Component {
    constructor(props){
        super(props);

        this.defineName = this.defineName.bind(this);
        this.togglePopup = this.togglePopup.bind(this);

        this.state = {
            opponentName: " "
        }
    }

    render() {
        return (
            <Modal isOpen={this.props.popupOpen} centered={true} toggle={this.togglePopup}>
                <ModalBody>
                    {this.defineName()}
                </ModalBody>
                <ModalFooter>
                    <Button color="secondary" onClick={this.togglePopup}>Close</Button>
                </ModalFooter>
            </Modal>
        )
    }

    defineName() {
        return (
            <TextField />
        )
    }
    //Use to render table once we have info to populate
    renderInvitations() {
        return (
            <tr>
                <td>INVITATION</td>
                <td>2 DAYS AGO</td>
                <td>
                    <Button style={{backgroundColor: 'green'}}>✓</Button>
                    <Button style={{backgroundColor: 'red'}}>X</Button>
                </td>
            </tr>
        )
    }

    togglePopup() {
        this.props.togglePopup();
    }

}