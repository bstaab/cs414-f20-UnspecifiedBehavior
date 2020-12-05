import React from 'react';
import {Avatar, Card, CardMedia, CardContent, Typography, Grid, Box} from "@material-ui/core";
import {Row} from 'reactstrap';
import PersonIcon from '@material-ui/icons/Person';



function Profile() {
    return (
        <div className={'profile-image'}>
            {displayCard}
        </div>
    )
}

function displayCard() {
    return (
        <div>
            <Grid
                container direction={"column"} justify={"center"} alignItems={"center"} alignContent={"center"}
                spacing={8}
            >

            </Grid>
        </div>
    )
}

//TODO: display stats from database
function displayStats() {
    return (
        <div>

        </div>
    )

}

//TODO: get the user stats from the database
function getUserStats() {

}

export default Profile;