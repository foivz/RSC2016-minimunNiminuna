import React from 'react';

// Router
import { hashHistory} from 'react-router';

// Material UI
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import Checkbox from 'material-ui/Checkbox';
import IconArrow from 'material-ui/svg-icons/navigation/chevron-right';

export default class Landing extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            dialogOpen: false,
        };
    }

    componentDidMount() {
        TweenMax.to('.article', 1.4, {
            top: '50%',
            delay: 1,
            opacity: 1
        });
    }

    handleOpenDialog() {
        this.setState({ dialogOpen: true });
    }

    handleCloseDialog() {
        this.setState({ dialogOpen: false });
    }

    handleUsernameChange(event) {
        this.setState({ username: event.target.value });
    }

    handlePasswordChange(event) {
        this.setState({ password: event.target.value });
    }

    userLoginAndRedirect() {
        var username = this.state.username;
        TweenMax.to('.overlay--hidden', 2, {
            bottom: 0,
            opacity: 1,
            delay: 0.5,
            onComplete: () => {
                if (username === 'user')
                    hashHistory.push('/user');
                else
                    hashHistory.push('/moderator');
            }
        });
    }

    render() {

        const style = {
            labelStyle: {
                fontSize: '20px'
            },
            buttonIconStyle: {
                marginTop: -7
            },
            checkboxStyle: {
                marginTop: '20px'
            }
        };
        
        const actions = [
            <FlatButton
                label="Cancel"
                primary={true}
                onTouchTap={this.handleCloseDialog.bind(this)}
            />,
            <FlatButton
                label="Sign In"
                primary={true}
                onTouchTap={this.userLoginAndRedirect.bind(this)}
            />
        ];

        return(
            <div id="landing">

                <article className="article">
                    <h1 className="heading">QUEASE</h1>
                    <label className="label-desc">Test your knowledge and sharpen your skills</label>
                    <RaisedButton
                        label="GET STARTED"
                        primary={true}
                        labelStyle={style.labelStyle}
                        onTouchTap={this.handleOpenDialog.bind(this)}
                        icon={<IconArrow style={style.buttonIconStyle}/>}
                    />
                </article>
                
                <video autoPlay loop id="video-background" muted>
                    <source
                        src="static/video/video-bg.mp4"/>
                </video>

                <label className="label-copyright">
                    @RSC 2016 - Team minimun niminuna
                </label>

                <Dialog
                    title="Sign In"
                    actions={actions}
                    open={this.state.dialogOpen}
                    style={style.dialogStyle}
                >
                    <TextField
                        floatingLabelText="Username"
                        hintText="Username"
                        fullWidth={true}
                        onChange={this.handleUsernameChange.bind(this)}
                    /><br />
                    <TextField
                        hintText="Password"
                        floatingLabelText="Password"
                        type="password"
                        fullWidth={true}
                        onChange={this.handlePasswordChange.bind(this)}
                    /><br />
                    <Checkbox
                        label="Remember me"
                        value="checked"
                        style={style.checkboxStyle}
                    />
                </Dialog>

                <div className="overlay overlay--hidden"></div>

            </div>
        )
    }
};