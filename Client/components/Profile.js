import React, {useState} from 'react';
import {Typography, Grid, TextField, InputAdornment} from "@material-ui/core";
import {sendPostRequest} from "../hooks/API";
import SearchIcon from "@material-ui/icons/Search";

export function Profile(props) {

    const [currentUserData, setCurrentUserData] = useState(props.userData);
    const [searchUser, setSearchUser] = useState('');

    function getUserStats() {
        sendPostRequest('userData', {'username': searchUser})
            .then(r => {
                let valid = r.data.valid;
                if (!valid) props.produceSnackBar("Invalid Username", "info");
                else setCurrentUserData(r.data);
            })
    }

    return (
        <>
            <div style={{padding: 20}}>
                <TextField id="standard-search" label="User" type="search"
                           onChange={(e) => setSearchUser(e.target.value)}
                           onKeyDown={(e) =>
                           {
                               if(e.key === 'Enter') getUserStats();
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
                <Typography variant={"h6"} align={"center"}>Username: {currentUserData.username}</Typography>
                <Typography variant={"h6"} align={"center"}>Email: {currentUserData.email}</Typography>
                <Typography variant={"h6"} align={"center"}>Games Won: {currentUserData.gamesWon}</Typography>
                <Typography variant={"h6"} align={"center"}>Games Lost: {currentUserData.gamesLost}</Typography>
            </Grid>
        </>
    )

}



//TODO: get the user stats from the database


export default Profile;