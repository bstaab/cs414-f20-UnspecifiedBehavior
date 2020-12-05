import React from "react";
import {Box, Grid, Paper} from "@material-ui/core";

const ProfileLayout = props =>
{
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