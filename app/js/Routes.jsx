import React from 'react';

// React Router
import {
    Router,
    Route,
    hashHistory,
    IndexRoute
} from 'react-router';

// Pages
import Main from './pages/Main.jsx';
import Landing from './pages/Landing.jsx';

// Navigation
import Navigation from './components/navigation/Navigation.jsx';

import Component from './components/Component.jsx';

export default class Routes extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return(
            <Router history={hashHistory}>
                <Route path="/" component={Landing} />
                <Route path="/app" component={Navigation}>
                    <IndexRoute component={Main}/>
                    <Route path="/app/about" component={Component}/>
                </Route>
            </Router>
        );
    }
};