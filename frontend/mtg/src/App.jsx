import { useState } from 'react'
import './App.css'
import Hint from './Hint'

export default function App() {
  const [count, setCount] = useState(0)
  

  const handleClick = () => {

  }

  return (
    <>
    <div>
      <button>Dette er en test knapp</button>
      <Hint></Hint>
    </div>
      
      

    </>
  )
}

