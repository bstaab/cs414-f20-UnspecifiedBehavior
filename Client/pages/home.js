import React, {Component, useState} from 'react';
import HomeNavigation from "./HomeNavigation";
import Background from "../static/images/homeBackground.jpg";

import {useHistory} from "react-router";

    const Home = props => {
        const history = useHistory();
      return (
          <div>
             <HomeNavigation width={props.width} height={props.height} history={history} />
          </div>
      )
    };

   export default Home;