import React from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';

export default class Main extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return(
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <Card>
                            <CardHeader
                                title="Moderator - Jennifer Lensey"
                                subtitle="Biology Quiz"
                                avatar="https://www.drupal.org/files/project-images/quiz_317_1081_q_image_large_0.jpg"
                            />
                            <CardMedia
                                overlay={<CardTitle title="Biology Quiz" subtitle="For sixth grade" />}
                            >
                                <img src="https://www.drupal.org/files/project-images/quiz_317_1081_q_image_large_0.jpg" />
                            </CardMedia>
                            <CardTitle title="Quiz" subtitle="Enter code: 45323" />
                            <CardText>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                Donec mattis pretium massa. Aliquam erat volutpat. Nulla facilisi.
                            </CardText>
                            <CardActions>
                                <FlatButton label="Statistics" />
                                <FlatButton label="Enter Quiz" />
                            </CardActions>
                        </Card>
                    </div>

                    <div className="col-md-4">
                        <Card>
                            <CardHeader
                                title="Moderator - Jennifer Lensey"
                                subtitle="Biology Quiz"
                                avatar="https://www.drupal.org/files/project-images/quiz_317_1081_q_image_large_0.jpg"
                            />
                            <CardMedia
                                overlay={<CardTitle title="Biology Quiz" subtitle="For sixth grade" />}
                            >
                                <img src="https://www.drupal.org/files/project-images/quiz_317_1081_q_image_large_0.jpg" />
                            </CardMedia>
                            <CardTitle title="Quiz" subtitle="Enter code: 45323" />
                            <CardText>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                Donec mattis pretium massa. Aliquam erat volutpat. Nulla facilisi.
                            </CardText>
                            <CardActions>
                                <FlatButton label="Statistics" />
                                <FlatButton label="Enter Quiz" />
                            </CardActions>
                        </Card>
                    </div>

                    <div className="col-md-4">
                        <Card>
                            <CardHeader
                                title="Moderator - Jennifer Lensey"
                                subtitle="Biology Quiz"
                                avatar="https://www.drupal.org/files/project-images/quiz_317_1081_q_image_large_0.jpg"
                            />
                            <CardMedia
                                overlay={<CardTitle title="Biology Quiz" subtitle="For sixth grade" />}
                            >
                                <img src="https://www.drupal.org/files/project-images/quiz_317_1081_q_image_large_0.jpg" />
                            </CardMedia>
                            <CardTitle title="Quiz" subtitle="Enter code: 45323" />
                            <CardText>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                Donec mattis pretium massa. Aliquam erat volutpat. Nulla facilisi.
                            </CardText>
                            <CardActions>
                                <FlatButton label="Statistics" />
                                <FlatButton label="Enter Quiz" />
                            </CardActions>
                        </Card>
                    </div>
                </div>
            </div>
        );
    }
};