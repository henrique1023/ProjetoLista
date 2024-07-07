import React, {useState, useEffect} from "react";
import { Link, useNavigate  } from "react-router-dom";
import { FiPower, FiEdit, FiTrash2 } from "react-icons/fi";

import './Styles.css'
import logoImage from '../../assets/logo.svg'
import api from "../../services/api";

export default function Listas(){
    const [listas, setListas] = useState([])
    const [page, setPage] = useState(1)

    const navigate = useNavigate();

    const accessToken = localStorage.getItem('accessToken')
    const username = localStorage.getItem('username') !== null ? localStorage.getItem('username') : ''

    function verifyToken(){
        if(accessToken === undefined || accessToken === '' || accessToken === null){
            return true
        }else{
            return false
        }
    }

    async function editLista(nomeLista){
        try {
            navigate(`/list/new/${nomeLista}`)
        } catch (error) {
            alert('Erro ao editar lista!')
        }
        
    }

    async function deleteLista(nomeLista){
        try {
            await api.delete(`api/lists/v1/${nomeLista}`, { headers: {
                Authorization: `Bearer ${accessToken}`
            }})

            setListas(listas.filter(list => list.nome !== nomeLista))
        } catch (error) {
            alert('Erro ao deletar lista!')
        }
       
    }

    async function logout(){
        localStorage.clear();
        navigate('/')
    }

    async function fetchMoreListas(){
        try {
            const response = await api.get('api/lists/v1', { 
            headers: {
                Authorization: `Bearer ${accessToken}`
            }
            })

            console.log(response.data)

            setListas([...listas, ...response.data])
        } catch (error) {
            
        }
        
    }

    useEffect(() => {
        console.log(accessToken)
        if(verifyToken()){
            navigate('/')
        }else{
            fetchMoreListas()
        }
        
    }, []); 
    
    return (
       <div className="book-container">
            <header>
                <img src={logoImage} alt="Logo" />
                <span>Welcome, <strong>{username.toUpperCase}</strong>!</span>
                <Link className="button" to="/list/new/0">Add new List</Link>
                <button  onClick={() => logout()}  type="button">
                    <FiPower size={18} color="#251fc5"/>
                </button>
            </header>
            <h1>Registered Listas</h1>
            <ul>
                {listas.map(lista => (
                    <li key={lista.id}>
                        <strong>Nome:</strong>
                        <p>{lista.nome}</p>
                        <strong>Descrição:</strong>
                        <p>{lista.descricao}</p>
                        <strong>Quantidade de músicas:</strong>
                        {lista.musicas !== undefined ? (
                            <div>
                                <strong>Músicas:</strong>
                                <ul>
                                    {lista.musicas.map(musica => (
                                        <li key={musica.id}>
                                            <strong>Artista:</strong>
                                            <p>{musica.artista}</p>
                                            <strong>Ano:</strong>
                                            <p>{musica.ano}</p>
                                            <strong>Álbum:</strong>
                                            <p>{musica.album}</p>
                                            <strong>Genero:</strong>
                                            <p>{musica.genero}</p>
                                            <strong>Titulo:</strong>
                                            <p>{musica.titulo}</p>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        ) : ''}

                        <button onClick={() => editLista(lista.nome)} ype="button">
                            <FiEdit size={20} color="#251fc5"/>
                        </button>
                        <button onClick={() => deleteLista(lista.nome)} type="button">
                            <FiTrash2 size={20} color="#251fc5"/>
                        </button>
                    </li>
                ))}
                
            </ul>

       </div>
    )
}