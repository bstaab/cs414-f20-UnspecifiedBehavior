import React from 'react';
import {Typography, Grid, TextField, InputAdornment} from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import {sendPostRequest} from "../components/API";


function Profile(props) {
    getUserStats(props)
    return (
        displayStats()
    )
}

//TODO: display stats from database
function displayStats() {
    return (
        <>
            <Grid
                container direction={"column"} justify={"center"} alignItems={"center"} alignContent={"center"}
                spacing={5}
            >
                <Grid item style={{paddingTop: '50px', paddingBottom: '14px'}}>
                    <Typography variant={"h4"} align={"center"}>User Profile</Typography>
                </Grid>
                <Typography variant={"h6"} align={"center"}>Username: </Typography>
                <Typography variant={"h6"} align={"center"}>Email: </Typography>
                <Typography variant={"h6"} align={"center"}>Games Won: </Typography>
                <Typography variant={"h6"} align={"center"}>Games Lost: </Typography>
            </Grid>
        </>
    )

}

//TODO: get the user stats from the database
function getUserStats(props) {
    sendPostRequest('userdata', {'username': props.userData})
        .then(r => {
            let valid = r.data.valid;
            if (!valid) props.produceSnackBar("Invalid Username", "error");
        })

}

export default Profile;