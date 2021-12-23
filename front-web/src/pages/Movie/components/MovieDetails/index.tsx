import React from 'react';
import { useParams } from 'react-router-dom';
import './style.scss';

type ParamsType = {
    movieId: string;
}

const MovieDetails = () => {
    const {movieId} = useParams<ParamsType>();

    console.log(movieId);

    return(
        <div className = "movies-details-container">
            <h1>Movies details</h1>    
        </div>
    );

};


export default MovieDetails;