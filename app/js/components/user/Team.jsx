import React from 'react';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import {createTeam, getIcons} from '../../utils/appService';
import Dialog from 'material-ui/Dialog';

export default class Team extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            dialogOpen: false,
            loading: true
        };
    }

    componentDidMount() {
        getIcons((res) => {
            console.log(res);
            this.setState({
                loading: false,
                icons: res
            })
        });
    }

    setName(event) {
        this.setState({
            name: event.target.value
        })
    }

    setDescription(event) {
        this.setState({
            description: event.target.value
        })
    }

    setIconID(id, url) {
        this.setState({
            iconID: id,
            url: url
        })
    }

    createTeam() {
        var data = {
            'name': this.state.name,
            'description': this.state.description,
            'icon': {
                'idIcon': this.state.iconID,
                'url': this.state.url
            }
        };

        createTeam(data, (res) => {
            this.setState({
                dialogOpen: true
            });
        });
    }

    handleClose() {
        this.setState({
            dialogOpen: false
        });
    }

    renderIcons() {
        return this.state.icons.map(function(elem, key) {
            return (
                <div className="col-md-4" key={key}>
                    <img src={elem.url} className="team-icon" alt="" onClick={this.setIconID.bind(this, elem.idIcon, elem.url)}/>
                </div>
            );
        }, this);
    }

    render() {

        const actions = [
            <FlatButton
                label="Close"
                primary={true}
                keyboardFocused={true}
                onTouchTap={this.handleClose.bind(this)}
            />
        ];

        if(!this.state.loading)
            return(
                <div className="container team-container">
                    <div className="row">
                        <div className="col-md-6 col-md-offset-3">
                            <TextField
                                hintText="Team Name"
                                floatingLabelText="Team Name"
                                type="text"
                                fullWidth={true}
                                onChange={this.setName.bind(this)}
                            /><br />
                            <TextField
                                hintText="Description"
                                floatingLabelText="Description"
                                type="text"
                                fullWidth={true}
                                onChange={this.setDescription.bind(this)}
                            /><br />

                            <div className="row icon-container">
                                {this.renderIcons()}
                            </div>


                            <Dialog
                                title="Dialog With Actions"
                                actions={actions}
                                modal={false}
                                open={this.state.dialogOpen}
                                onRequestClose={this.handleClose.bind(this)}
                            >
                                New team successfully created!
                            </Dialog>

                            <RaisedButton
                                label="Add team"
                                primary={true}
                                fullWidth={true}
                                onTouchTap={this.createTeam.bind(this)}
                            />
                        </div>
                    </div>
                </div>
            );
        return(<div></div>);
    }
};
