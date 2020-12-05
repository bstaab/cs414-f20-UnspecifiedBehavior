import React from 'react';
import {Avatar, Card, CardMedia, CardContent, Typography, Grid, Box} from "@material-ui/core";
import {Row} from 'reactstrap';
import PersonIcon from '@material-ui/icons/Person';


function Profile(props) {
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
                spacing={8}
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
function getUserStats() {

}

export default Profile;