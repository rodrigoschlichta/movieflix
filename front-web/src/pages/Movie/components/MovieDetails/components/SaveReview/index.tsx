import { useState } from "react"
import { Link } from "react-router-dom"
import { toast } from "react-toastify"
import history from "../../../../../../core/utils/history"
import { makePrivateRequest } from "../../../../../../core/utils/request"

import './style.scss'

type Props = {
  movieId: string
}

const SaveReview = ({ movieId }: Props) => {
  const [review, setReview] = useState('')

  const saveReview = () => {
    const payload = {
      movieId,
      text: review
    }

    makePrivateRequest({
      url: '/reviews',
      method: 'POST',
      data: payload
    }).then(() => {
      history.push(`/movies`)
      toast.success('Avaliação salva', { delay: 500 })
    }).catch(() => {
      toast.error('Ocorreu um erro ao salvar')
    })
  }

  const handleChangeReviewText = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setReview(event.target.value)
  }

  return (
    <div className="post-new-review-container">
      <textarea
        value={ review }
        placeholder="Digite aqui sua avaliação"
        className="new-review-text"
        onChange={ handleChangeReviewText }
      />
      <Link to="/movies">
      <button
        type = "submit"    
        className="new-review-button"
        onClick={ saveReview }
      >
        <span className="new-review-button-text">Salvar avaliação</span>
      </button>
      </Link>
    </div>
  )
}

export default SaveReview