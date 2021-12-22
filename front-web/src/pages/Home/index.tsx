import React from 'react';
import { ReactComponent as MainImage } from '../../core/images/main.svg';
import './style.scss';

const Home = () => (

  <div className="home-container">
    <div className="home-content">
      <h1 className="home-title">Avalie Filmes</h1>
      <div>
        <h3 className="home-subTitle">
          Diga o que você achou do seu filme favorito
        </h3>
      </div>
      <div className="home-image">
        <MainImage />
      </div>
    </div>
    <div className="home-form-auth">
      <form>
        <h1 className="home-login">Login</h1>
        <input type="email" placeholder="E-mail" className="form-input-email" name="username"></input>
        <input type="password" placeholder="Senha" className="form-input-senha" name="password"></input>
        <div>
          <button className="btn-form">
            <h3 className="btn-button">FAZER LOGIN</h3>
          </button>
        </div>
      </form>
    </div>
  </div>
 

);

export default Home;