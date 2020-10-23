import React from "react";

import { useHistory, useLocation } from "react-router-dom";

import {Box, Grid, Link, Paper} from "@material-ui/core";

const LoginLayout = props =>
{
    const history = useHistory();
    const location = useLocation();

    return (
        <>
            <div className={"background-image"} />
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
                                {
                                    location.pathname === "/" ?
                                        <div style={{width: "100%", position: "absolute", bottom: "30px", textAlign: "center"}}>
                                            <Link onClick={() => history.push("/register")}>
                                                Not Registered? Sign Up Here!
                                            </Link>
                                        </div> : null
                                }
                            </Paper>
                        </Grid>
                    </Grid>
                </Box>
            </div>
        </>
    );
};

export default LoginLayout;