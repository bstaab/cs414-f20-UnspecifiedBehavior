import React, {useEffect, useState} from 'react'
import {IconButton} from "@material-ui/core";
import {sendPostRequest} from "../components/API";
import {Table, Modal} from 'reactstrap';
import CheckIcon from '@material-ui/icons/Check';
import CloseIcon from '@material-ui/icons/Close';



function Invite(props) {
    const [inviteData, setInviteData] = useState([]);

    useEffect(() => {
        sendPostRequest('getAllMessages', {'username': props.userData}).then(
            r => {
                //console.log(r.data.from);
                setInviteData(r.data.from);
            }
        )
    }, [])

      return (
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
                  {inviteData.map(opponentName =>
                      <tr>
                          <td>{opponentName}</td>
                          <td>
                              <IconButton><CheckIcon/></IconButton>
                              <IconButton><CloseIcon/></IconButton>
                          </td>
                      </tr>

                  )}
                  </tbody>
              </Table>

          </Modal>
      )
}

export default Invite;