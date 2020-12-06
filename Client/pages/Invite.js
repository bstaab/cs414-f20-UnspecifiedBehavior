import React, {useEffect, useState} from 'react'
import {IconButton} from "@material-ui/core";
import {sendPostRequest} from "../components/API";
import {Table, Modal} from 'reactstrap';
import CheckIcon from '@material-ui/icons/Check';
import CloseIcon from '@material-ui/icons/Close';
import Match from './Match'

function Invite(props) {
    const [fromUserData, setFromUserData] = useState([]);
    const [messagesData, setMessagesData] = useState([]);
    const [chessPopup, setChessPopup] = useState(false)

    useEffect(() => {
        sendPostRequest('getAllMessages', {'username': props.userData}).then(
            r => {
                if (r.data.valid) {
                    console.log(r.data);
                    setFromUserData(r.data.from);
                    setMessagesData(r.data.messages)
                }
                else {
                    props.produceSnackBar('No messages', 'info');
                }


            }
        )
    }, [])

    function createNewGame(opponentName) {
        sendPostRequest('newChessMatch', {'to': opponentName, 'from': props.userData})
            .then(
                r => {
                    console.log(r.data);
                    console.log(opponentName);
                    if (r.data.valid) {
                        props.setFen(r.data.fen);
                        props.produceSnackBar('Game Successfully Accepted', 'success');
                        setChessPopup(!chessPopup);
                    }
                    else {
                        props.produceSnackBar('Game acceptance failed', 'error');
                    }
                }
            )

    }

      return (
          <div>
              {
                  chessPopup ? <Match/> : null
              }
              <Modal
                  isOpen={props.openInviteModal}
                  centered={true}
                  toggle={() => props.setOpenInviteModal(false)}
              >
                  <Table striped responsive>
                      <thead>
                      <tr>
                          <th>Opponent</th>
                          <th>Option</th>
                          <th></th>
                      </tr>
                      </thead>
                      <tbody>
                      {fromUserData.map(opponentName =>
                          <tr>
                              <td>{opponentName}</td>
                              <td>
                                  <IconButton onClick={() => createNewGame(opponentName)}><CheckIcon/></IconButton>
                                  <IconButton><CloseIcon/></IconButton>
                              </td>
                          </tr>

                      )}
                      </tbody>
                  </Table>

              </Modal>
          </div>
      )
}



export default Invite;