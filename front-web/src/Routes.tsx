import React from 'react'
import Navbar from './core/components/Navbar';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Home from './pages/Home';
import Movie from './pages/Movie';
import MovieDetails from './pages/Movie/components/MovieDetails';

const Routes = () => (
    <BrowserRouter>
        <Navbar />
        <Switch>
            <Route path="/" />
            <Route path="/movies" /> 
            <Route path="/movies/:movieId"/>        
        </Switch>
    </BrowserRouter>
);

export default Routes;