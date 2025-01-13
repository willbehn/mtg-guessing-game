import { useState } from 'react'
import './App.css'
import HintList from './GuessTheMtgCard'
import GuessTheMtgCard from './GuessTheMtgCard'

export default function App() {
  const [count, setCount] = useState(0)
  

  const handleClick = () => {

  }

  return (
    <>
      <GuessTheMtgCard></GuessTheMtgCard>
    </>
  )
}

