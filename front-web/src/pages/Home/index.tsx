import { useForm } from 'react-hook-form';
import { useState } from 'react'

import { saveSessionData } from '../../core/utils/auth';
import { makeLogin } from '../../core/utils/request';
import { ReactComponent as MainImage } from '../../core/images/main.svg';
import './style.scss';

type FormData = {
  username : string;
  password : string;
}

const Home = () => {

    const {register, handleSubmit} = useForm<FormData>();
    const [hasError, setHasError] = useState(false);
    

    const onSubmit = (data: FormData) => {
      makeLogin(data)
        .then(response => {
          setHasError(false)
          saveSessionData(response.data)
        })
        .catch(() => {
          setHasError(true)
        })
    }
  
  return(

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
      <form onSubmit = {handleSubmit(onSubmit)}>
        <h1 className="home-login">Login</h1>
        {hasError && (
              <div className="alert">
                <p className="alert-text">Usuário ou senha inválidos!</p>
                <span className="close" onClick={ () => setHasError(false) }>X</span>
              </div>
            )}
        <input 
            {...register("username", {required: true})}
            type="email" 
            placeholder="E-mail" 
            className="form-input-email" 
            data-testid= "email">
            </input>

        <input 
            {...register("password",{required: true})}
            type="password" 
            placeholder="Senha" 
            className="form-input-senha" 
            data-testid="password">
            </input>
        <div>
          <button type = "submit" className="btn-form">
            <h3 className="btn-button">FAZER LOGIN</h3>
          </button>
        </div>
      </form>
    </div>
  </div>
 
    )
};

export default Home;


