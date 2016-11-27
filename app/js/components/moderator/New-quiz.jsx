import React from 'react'

import {getQuestions, getAwards} from '../../utils/appService';

// Material UI
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import DatePicker from 'material-ui/DatePicker';
import TimePicker from 'material-ui/TimePicker';
import Checkbox from 'material-ui/Checkbox';

import {
    Step,
    Stepper,
    StepLabel,
} from 'material-ui/Stepper';
import ExpandTransition from 'material-ui/internal/ExpandTransition';


export default class AddQuiz extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            selectBoxValue: 1,
            loadingStepper: false,
            finishedStepper: false,
            stepIndex: 0,
            loadingQuestions: true,
            loadingAwards: true,
        };
        this.handleNext = this.handleNext.bind(this);
        this.handlePrev = this.handlePrev.bind(this);
    }

    componentDidMount() {
        getQuestions((res) => {
            this.setState({
                loadingQuestions: false,
                questions: res
            });
        });

        getAwards((res) => {
            console.log(res);
            this.setState({
                loadingAwards: false,
                awards: res
            })
        });
    }

    dummyAsync (cb) {
        this.setState({loading: true}, () => {
            setTimeout(cb, 500);
        });
    };

    handleNext () {
        const {stepIndex} = this.state;
        if (!this.state.loading) {
            this.dummyAsync(() => this.setState({
                loading: false,
                stepIndex: stepIndex + 1,
                finished: stepIndex >= 2,
            }));
        }
    };

    handlePrev () {
        const {stepIndex} = this.state;
        if (!this.state.loading) {
            this.dummyAsync(() => this.setState({
                loading: false,
                stepIndex: stepIndex - 1,
            }));
        }
    };

    renderCheckbox(data) {
        return data.map(function(elem, key) {
            return (
                <Checkbox
                    key={key}
                    label={elem.question || elem.name}
                    style={{marginTop: '5px', marginBottom: '5px'}}
                />
            );
        }, this);
    }


    getStepContent(stepIndex) {
        switch (stepIndex) {
            case 0:
                return (
                    <div>
                        <div className="container">
                            <p>Quiz description</p>
                            <TextField
                                floatingLabelText="Name"
                                hintText="Name"
                                fullWidth={true}
                            /><br />
                            <TextField
                                floatingLabelText="Description"
                                hintText="Description"
                                fullWidth={true}
                            /><br />
                            <SelectField
                                floatingLabelText="Category"
                                value={this.state.selectBoxValue}
                                onChange={this.handleChange}
                                fullWidth={true}
                            >
                                <MenuItem width={150} value={1} primaryText="Informatic" />
                                <MenuItem width={150} value={2} primaryText="Mathematic" />
                                <MenuItem width={150} value={3} primaryText="Biology" />

                            </SelectField>
                            <br />
                            <br />
                            <DatePicker
                                hintText="Date begin"
                                container="inline"
                                mode="landscape"
                                fullWidth={true}
                            />
                            <DatePicker
                                hintText="Date end"
                                container="inline"
                                mode="landscape"
                                fullWidth={true}
                            />
                            <TimePicker
                                hintText="Time begin"
                                autoOk={true}
                                fullWidth={true}
                            />
                            <TimePicker
                                hintText="Time end"
                                autoOk={true}
                                fullWidth={true}
                            />
                        </div>
                    </div>
                );
            case 1:
                if(!this.state.loadingQuestions)
                    return (
                        <div>
                            {this.renderCheckbox(this.state.questions)}
                        </div>
                    );
                return(<div></div>);
            case 2:
                if(!this.state.loadingAwards)
                    return (
                        <div>
                            {this.renderCheckbox(this.state.awards)}
                        </div>
                    );
                return(<div></div>);
            default:
                return 'You\'re a long way from home sonny jim!';
        }
    }

    renderContent() {
        const {finished, stepIndex} = this.state;
        const contentStyle = {margin: '0 16px', overflow: 'hidden'};

        if (finished) {
            alert("Finished");
        }

        return (
            <div style={contentStyle}>
                <div>{this.getStepContent(stepIndex)}</div>
                <div style={{marginTop: 24, marginBottom: 12}}>
                    <FlatButton
                        label="Back"
                        disabled={stepIndex === 0}
                        onTouchTap={this.handlePrev}
                        style={{marginRight: 12}}
                    />
                    <RaisedButton
                        label={stepIndex === 2 ? 'Finish' : 'Next'}
                        primary={true}
                        onTouchTap={this.handleNext}
                    />
                </div>
            </div>
        );
    }

    render() {
        const {loading, stepIndex} = this.state;

        return (
            <div  className="general-container" style={{width: '100%', maxWidth: 800}}>
                <Stepper activeStep={stepIndex}>
                    <Step>
                        <StepLabel>General Quiz informations</StepLabel>
                    </Step>
                    <Step>
                        <StepLabel>Quiz questions</StepLabel>
                    </Step>
                    <Step>
                        <StepLabel>Awards</StepLabel>
                    </Step>
                </Stepper>
                <ExpandTransition loading={loading} open={true}>
                    {this.renderContent()}
                </ExpandTransition>
            </div>
        );
    }
}