import React from 'react';
import {Avatar, Card, CardMedia, CardContent, Typography, Grid, Box, Button} from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import {sendPostRequest} from "../components/API";


function Profile(props) {
    return (
        displayStats()
    )
}

function displayStats() {
    return (
        <>
            <Button onClick={getOtherUserStats}>
                <SearchIcon/>
            </Button>
            <Grid
                container direction={"column"} justify={"center"} alignItems={"center"} alignContent={"center"}
                spacing={8}
            >
                <Grid item>
                </Grid>
                <Typography variant={"h6"} align={"center"}>Username: </Typography>
                <Typography variant={"h6"} align={"center"}>Email: </Typography>
                <Typography variant={"h6"} align={"center"}>Games Won: </Typography>
                <Typography variant={"h6"} align={"center"}>Games Lost: </Typography>
            </Grid>
        </>
    )

}

function getStats() {

}



//TODO: get the user stats from the database
function getOtherUserStats() {

}

export default Profile;