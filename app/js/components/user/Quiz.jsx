import React from 'react';
import {fetchQuizByID, sendAudio, sendImage} from '../../utils/appService';

import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import {RadioButton, RadioButtonGroup} from 'material-ui/RadioButton';
import AudioRecorder from 'react-audio-recorder';
import IconArrow from 'material-ui/svg-icons/navigation/chevron-right';
import TextField from 'material-ui/TextField';
import Snackbar from 'material-ui/Snackbar';
import Dialog from 'material-ui/Dialog';
import {hashHistory} from 'react-router';
import Divider from 'material-ui/Divider';
import Dropzone from 'react-dropzone';
import ReactCountdownClock from 'react-countdown-clock';


export default class Quiz extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: true,
            currentQuestionIndex: 0,
            checkboxAnswer: 0,
            correctAnswerSum: 0,
            infoOpen: false,
            infoMessage: '',
            dialogOpen: false
        };
    }

    componentWillMount() {
        var quizID = this.props.params.id;
        fetchQuizByID(quizID, (res) => {
            this.setState({
                loading: false,
                quiz: res,
                timer: res.questions[0].cat.time
            });
        });
    }

    renderQuestion(index) {
        var questions = this.state.quiz.questions;
        switch(questions[index].cat.idCat) {
            case 1: // Textbox
                return this.renderTextbox(questions[index]);
                break;
            case 2: // Checkbox
                return this.renderCheckbox(questions[index].allAnswers, questions[index].question);
                break;
            case 4: // Images
                return this.renderImageContainer(questions[index].allAnswers, questions[index].question);
                break;
            case 5:
                return this.renderImageUpload(questions[index].question);
                break;
        }
        this.setCountdown();
    }

    renderImageUpload(question) {
        return (
            <Dropzone
                onDrop={this.onDrop.bind(this)}
                multiple={false}
                className="dropzone"
            >
                <h4 className="dropzone-text">{question}</h4>
            </Dropzone>
        );
    }

    setCountdown() {

    }

    onDrop(files) {
        sendImage(files[0], (res) => {
            var result = res.images[0].classifiers[0].classes[0];
            this.setState({
                infoMessage: result.name + " " + parseInt(result.score * 100) + "%",
                infoOpen: true
            });
        });
    }

    renderImageContainer(answers, question) {
        return(
            <div>
                <h2>{question}</h2>
                <div className="row">
                    {this.renderImageSelect(answers)}
                </div>
            </div>
        );
    }

    renderImageSelect(answers) {
        answers = this.parseAnswers(answers);
        return answers.map(function(elem, key) {
            return (
                <div key={key} className="col-md-3">
                    <img src={elem} alt="Image" className="image-picker" onClick={() => this.setImageAnswer(elem)}/>
                </div>
            );
        }, this);
    }

    renderCheckbox(answers, question) {
        answers = this.parseAnswers(answers);
        return(
           <div>
               <h2>{question}</h2>
               <Divider />
               <RadioButtonGroup name="checkbox" onChange={this.setAnswer.bind(this)}>
                   {this.renderCheckboxAnswers(answers)}
               </RadioButtonGroup>
           </div>
        );
    }

    parseAnswers(answers) {
        return answers.split(';');
    }

    renderCheckboxAnswers(answers) {
        const style = {
            marginTop: '10px',
            marginBottom: '10px'
        };

        return answers.map(function(elem, key) {
            return(
                <RadioButton
                    key={key}
                    value={elem}
                    label={elem}
                    style={style}
                />
            );
        }, this);
    }

    renderTextbox(question) {
        return (
            <TextField
                value={this.state.textboxAnswer}
                hintText={question.question}
                onChange={this.setTextboxAnswer.bind(this)}
            />
        );
    }

    setImageAnswer(elem) {
        this.setState({
            imageAnswer: elem,
            infoOpen: false
        }, this.nextAnswer);
    }

    setAnswer(event, value) {
        this.setState({
            checkboxAnswer: value,
            infoOpen: false
        });
    }

    setTextboxAnswer(event, value) {
        this.setState({
            textboxAnswer: value,
            infoOpen: false
        });
    }

    checkAnswer() {
        this.timer._cancelTimer();
        var question = this.state.quiz.questions[this.state.currentQuestionIndex];
        if (this.state.checkboxAnswer == question.answer
            || this.state.textboxAnswer == question.answer
            || this.state.imageAnswer == question.answer) {
            this.setState({
                infoMessage: 'Answer is correct :)',
                infoOpen: true,
                correctAnswerSum: this.state.correctAnswerSum + 1
            });
        }
        else {
            this.setState({
                infoMessage: 'Answer is not correct',
                infoOpen: true,
            });
        }
    }

    nextAnswer() {
        var self = this;
        this.checkAnswer();

        this.timer._cancelTimer();

        if (this.state.quiz.questions.length === this.state.currentQuestionIndex + 1) {
            var percent = parseInt((this.state.correctAnswerSum / this.state.quiz.questions.length) * 100);
            this.setState({
               dialogOpen: true,
                result: 'Quiz score: ' + percent + '%'
            });
        }
        else {
            setTimeout(function() {
                self.setState({
                    currentQuestionIndex: self.state.currentQuestionIndex + 1,
                    timer: self.state.quiz.questions[self.state.currentQuestionIndex + 1].cat.time
                });
            }, 0);
        }
    }

    audioChange(data) {
        sendAudio(data.blob, (res) => {
            var result = res.results[0].alternatives[0];
            this.setState({
                infoMessage: result.transcript + " " + parseInt(result.confidence * 100) + "%",
                infoOpen: true,
                textboxAnswer: result.transcript
            });
        });
    }

    goToDashboard() {
        hashHistory.push('/user');
    }

    render() {

        const actions = [
            <FlatButton
                label="Close"
                primary={true}
                keyboardFocused={true}
                onTouchTap={this.goToDashboard}
            />
        ];

        if(!this.state.loading)
            return(
                <div>
                    <div className="text-center">
                        <ReactCountdownClock
                             seconds={this.state.timer}
                             color="#000"
                             alpha={0.2}
                             size={150}
                             onComplete={this.nextAnswer.bind(this)}
                             ref={ (timer) => {this.timer = timer; }}
                        />
                    </div>

                    <div className="container quiz-container">
                        {this.renderQuestion(this.state.currentQuestionIndex)}
                        <AudioRecorder onChange={this.audioChange.bind(this)} download={false}/>
                        <RaisedButton
                            label="Next Answer"
                            primary={true}
                            icon={<IconArrow/>}
                            onTouchTap={this.nextAnswer.bind(this)}
                        /><br />
                    </div>
                    <Dialog
                        title="Score"
                        actions={actions}
                        modal={false}
                        open={this.state.dialogOpen}
                    >
                        {this.state.result}
                    </Dialog>
                    <Snackbar
                        open={this.state.infoOpen}
                        message={this.state.infoMessage}
                        autoHideDuration={3000}
                    />
                </div>
            );

        return(<div></div>);
    }
};