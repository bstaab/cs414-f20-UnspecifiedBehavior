import React from "react";
import {Box, Grid, InputAdornment, Paper, TextField} from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";
import {sendPostRequest} from "../components/API";

const ProfileLayout = props =>
{
    function searchUser() {
        sendPostRequest('userdata', {'username': props.userData})
            .then(r => {
                let valid = r.data.valid;
                if (!valid) props.produceSnackBar("Invalid Username", "error");
            })
    }

    return (
        <>
            <div className={"profile-image"} />
            <div className={"content"}>
                <Box p={props.width < 500 ? 0 : 4}>
                    <Grid
                        container justify={"center"} alignContent={"center"} alignItems={"center"}
                        style={{height: props.width < 500 ? "95vh" : "92vh"}}
                    >
                        <Grid item xs={12} sm={12} md={5}>
                            <Paper elevation={8} style={{height: '80vh', position: "relative"}}>
                                <div style={{padding: 20}}>
                                    <TextField id="standard-search" label="Search for User" type="search"
                                               onKeyDown={(e) =>
                                               {
                                                   if(e.key === 'Enter') searchUser();
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
                                    container justify={"center"} alignContent={"center"} alignItems={"center"}
                                    style={{height: "100%"}}
                                >
                                    <Grid item style={{width: props.width < 500 ? '95%' : '80%'}}>
                                        {props.children}
                                    </Grid>
                                </Grid>
                            </Paper>
                        </Grid>
                    </Grid>
                </Box>
            </div>
        </>
    );
};


export default ProfileLayout;