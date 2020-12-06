import React, {useState} from 'react';
import {Typography, Grid, TextField, InputAdornment} from "@material-ui/core";
import {sendPostRequest} from "../components/API";
import SearchIcon from "@material-ui/icons/Search";


//TODO: display stats from database
export function Profile(props) {
    const [email, setEmail] = useState();
    const [gamesWon, setGamesWon] = useState()
    const [gamesLost, setGamesLost] = useState()
    const [username, setUsername] = useState()
    const [searchUser, setSearchUser] = useState('');

    getUserStats(props, props.userData, setUsername, setEmail, setGamesLost, setGamesWon)
    return (
        <>
            <div style={{padding: 20}}>
                <TextField id="standard-search" label="User" type="search"
                           onChange={(e) => {
                               setSearchUser(e.target.value);
                           }}
                           onKeyDown={(e) =>
                           {
                               if(e.key === 'Enter') getUserStats(props, searchUser, setUsername, setEmail, setGamesLost, setGamesWon);
                           }}
                           InputProps={{
                               startAdornment: (
                                   <InputAdornment position="start">
                                       <SearchIcon/>
                                   </InputAdornment>
                               ),
                           }}
                />
            </div>
            <Grid
                container direction={"column"} justify={"center"} alignItems={"center"} alignContent={"center"}
                spacing={5}
            >
                <Grid item style={{paddingTop: '50px', paddingBottom: '14px'}}>
                    <Typography variant={"h4"} align={"center"}>User Profile</Typography>
                </Grid>
                <Typography variant={"h6"} align={"center"}>Username: {username}</Typography>
                <Typography variant={"h6"} align={"center"}>Email: {email}</Typography>
                <Typography variant={"h6"} align={"center"}>Games Won: {gamesWon}</Typography>
                <Typography variant={"h6"} align={"center"}>Games Lost: {gamesLost}</Typography>
            </Grid>
        </>
    )

}



//TODO: get the user stats from the database
function getUserStats(props, searchUser, setUsername, setEmail, setGamesLost, setGamesWon) {
    sendPostRequest('userData', {'username': searchUser})
        .then(r => {
                //console.log(r.data);
            let valid = r.data.valid;
            if (!valid) props.produceSnackBar("Invalid Username", "info");
            else {
                setUsername(searchUser);
                setEmail(r.data.email.toString());
                setGamesLost(r.data.gamesLost.toString());
                setGamesWon(r.data.gamesWon.toString());
            }
        })
}

export default Profile;