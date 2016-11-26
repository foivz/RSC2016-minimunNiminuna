import React from 'react';
import {fetchQuizByID, sendAudio} from '../../utils/appService';

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
            });
        });
    }

    renderQuestion(index) {
        var questions = this.state.quiz.questions;
        console.log(questions);
        switch(questions[index].cat.idCat) {
            case 1: // Textbox
                return this.renderTextbox(questions[index]);
                break;
            case 2: // Checkbox
                return this.renderCheckbox(questions[index].allAnswers, questions[index].question);
                break;
        }
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
                hintText={question.question}
                onChange={this.setTextboxAnswer.bind(this)}
            />
        );
    }

    setAnswer(event, value) {
        this.setState({
            checkboxAnswer: value
        });
    }

    setTextboxAnswer(event, value) {
        this.setState({
            textboxAnswer: value
        });
    }

    checkAnswer() {
        var question = this.state.quiz.questions[this.state.currentQuestionIndex];
        if (this.state.checkboxAnswer == question.answer || this.state.textboxAnswer == question.answer) {
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
                    currentQuestionIndex: self.state.currentQuestionIndex + 1
                });
            }, 1500);
        }

    }

    audioChange(data) {
        sendAudio(data.blob, (res) => {
            console.log(res);
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
                    <div className="container quiz-container">
                        {this.renderQuestion(this.state.currentQuestionIndex)}
                        <AudioRecorder onChange={this.audioChange} />
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
                        autoHideDuration={2000}
                    />
                </div>
            );

        return(<div></div>);
    }
};