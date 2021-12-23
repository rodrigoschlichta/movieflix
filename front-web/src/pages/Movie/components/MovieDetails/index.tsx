import React from 'react';
import { useParams } from 'react-router-dom';
import { ReactComponent as MovieImage } from '../../../../core/images/imageDetails.svg'
import './style.scss';

type ParamsType = {
    movieId: string;
}

const MovieDetails = () => {
    const { movieId } = useParams<ParamsType>();

    console.log(movieId);

    return (
        <div className="movies-details-container">
            <div className="row">
                <div className="col-6 movies-image">
                    <MovieImage />
                </div>
                <div className="col-6 movie-title-details">
                    <h1>O Retorno do Rei</h1>
                    <div className="movie-year-details">
                        <h3>2013</h3>
                        <div className="movie-subTitle-details">
                            <h5>O olho do inimigo está se movendo.</h5>
                        </div>
                        <div className="movie-review-details">

                        </div>

                    </div>

                </div>
            </div>
        </div>

    );

};


export default MovieDetails;