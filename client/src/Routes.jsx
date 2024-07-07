import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/login/Index";
import Listas from "./pages/listas/Index";
import NewLista from "./pages/newLista";

export default props => (
    <Routes>
        <Route exact path='/' element={<Login/>}/>
        <Route path='/listas' element={<Listas/>}/>
        <Route path='/list/new/:listName' element={<NewLista/>}/>
    </Routes>
)
   