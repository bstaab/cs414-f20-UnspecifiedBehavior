import React, {useEffect, useState} from 'react'
import {IconButton} from "@material-ui/core";
import {sendPostRequest} from "../hooks/API";
import {Table, Modal} from 'reactstrap';

import CheckIcon from '@material-ui/icons/Check';
import CloseIcon from '@material-ui/icons/Close';

function ContinueGame(props) {
    const [matchListData, setMatchListData] = useState([]);

    useEffect(() => {
        sendPostRequest('getAllMessages', {'username': props.userData.username}).then(
            r => {
                if (r.data.valid) setMatchListData(r.data.from);
                else props.produceSnackBar('No Matches', 'info');
            }
        )
    }, [])

    function createNewGame(opponentName) {
        sendPostRequest('newChessMatch', {'user1': props.userData.username, 'user2': opponentName})
            .then(
                r => {
                    if (r.data.valid) {
                        sendPostRequest('userData', {username: opponentName}).then(
                            r => props.setOpponentUserData(r.data)
                        )
                        props.setFen(r.data.fen);
                        props.setIsWhiteUserInCurrentGame(r.data.whiteUser === props.userData.username)
                        props.produceSnackBar('Game Successfully Accepted', 'success');
                        props.setChessBoardPopupOpen(!props.chessBoardPopupOpen);
                        props.setContinueGamePopupOpen(!props.continueGamePopupOpen);
                    }
                    else {
                        props.produceSnackBar('Game Acceptance Failed', 'error');
                    }
                }
            )

    }

      return (
          <div>
              <Modal isOpen={props.continueGamePopupOpen} centered={true} toggle={() => props.setContinueGamePopupOpen(!props.continueGamePopupOpen)}>
                  <Table striped responsive>
                      <thead>
                      <tr>
                          <th>Opponent</th>
                          <th>Option</th>
                      </tr>
                      </thead>
                      <tbody>
                      {matchListData.map(opponentName =>
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

export default ContinueGame;