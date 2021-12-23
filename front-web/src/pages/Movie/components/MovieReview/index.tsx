import React from 'react';
import './style.scss';


const MovieReview = () =>(

    <div className="movieReview-container">
        <input className = "movie-review-input" type="text" placeholder="Deixe sua avaliação aqui" name = "review"></input>
        <button className = "movie-btn-review">Salvar Avaliação</button>
    </div>
    
);

export default MovieReview;