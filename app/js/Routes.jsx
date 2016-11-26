import React from 'react';

// React Router
import {
    Router,
    Route,
    hashHistory,
    IndexRoute
} from 'react-router';


import Landing from './pages/Landing.jsx';

// Navigation
import NavigationModerator from './components/moderator/NavigationModerator.jsx';
import NavigationUser from './components/user/NavigationUser.jsx';

// Components
import DashboardModerator from './components/moderator/Dashboard.jsx';
import DashboardUser from './components/user/Dashboard.jsx';
import AddModerator from './components/moderator/New-moderator.jsx';
import AddQuiz from './components/moderator/New-quiz.jsx';

export default class Routes extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return(
            <Router history={hashHistory}>
                <Route path="/" component={Landing} />
                <Route path="/moderator" component={NavigationModerator}>
                    <IndexRoute component={DashboardModerator}/>
                    <Route path="/moderator/new-moderator" component={AddModerator}/>
                    <Route path="/moderator/new-quiz" component={AddQuiz}/>
                </Route>
                <Route path="/user" component={NavigationUser}>
                    <IndexRoute component={DashboardUser}/>
                </Route>
            </Router>
        );
    }
};