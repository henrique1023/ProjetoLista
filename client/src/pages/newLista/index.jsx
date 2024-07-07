import React, {useState, useEffect} from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { FiArrowLeft } from "react-icons/fi";

import './styles.css';
import logoImage from '../../assets/logo.svg'
import api from "../../services/api";

export default function NewLista(){

    const [id, setId] = useState(null)
    const [nome, setNome] = useState('')
    const [descricao, setDescricao] = useState('')
    const [titulo, setTitulo] = useState('')
    const [artista, setArtista] = useState('')
    const [album, setAlbum] = useState('')
    const [ano, setAno] = useState('')
    const [genero, setGenero] = useState('')
    const [musicas, setMusicas] = useState([])

    const navigate = useNavigate();

    const {listName} = useParams();

    const accessToken = localStorage.getItem('accessToken')
    const refleshToken = localStorage.getItem('refleshToken')

    useEffect(() => {
        if (listName !== '0') {
           loadBook()
        } 
        
    }, [listName])

    async function loadBook(){
        try {
            const resp = await api.get(`api/lists/v1/${listName}`, { 
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }})

                setId(resp.data.id)
                setNome(resp.data.nome)
                setDescricao(resp.data.descricao)
                setMusicas(resp.data.musicas)
        } catch (error) {
            alert('Error load book' + error)
        }
    }

    async function saveOrUpdate(e){
        e.preventDefault();

        const data = {
            nome,
            descricao,
            musicas: musicas
        }

        try {
            if(listName === '0'){
                 await api.post('api/lists/v1', data, { 
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
                })
            }else {
                data.id = id
                await api.post('api/lists/v1', data, { 
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                }
                })
            }
           
            navigate('/listas')
        } catch (error) {
            alert('Error while recording!')
        }
    }

    const addMusicAndList = () => {
        return async (e) => {
            e.preventDefault();
            const data = {
                titulo,
                artista,
                album,
                ano,
                genero
            }
            var _musicas = [...musicas]
            _musicas.push(data)
            setMusicas(_musicas)
        }
    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logoImage} alt="Erudio" />
                    <h1>{listName === '0' ? 'Add' : 'Edit'} new List</h1>
                    <p>Enter the list information and click on '{listName === '0' ? 'Add' : 'Edit'}'</p>
                    <Link className="back-link" to={"/listas"}>
                        <FiArrowLeft size={16} color="#251fc5"/>
                        Home
                    </Link>
                </section>
                <form onSubmit={saveOrUpdate}>
                    <input type="text" placeholder="Nome" 
                    value={nome} onChange={e => setNome(e.target.value)}/>
                    <input type="text" placeholder="Descrição"
                    value={descricao} onChange={e => setDescricao(e.target.value)} />

                    <button className="button" type="submit">{listName === '0' ? 'Add' : 'Edit'}</button>
                </form>
            </div>
            <div className="content">
                <form onSubmit={addMusicAndList()}>
                    <input type="text" placeholder="Titulo" 
                    value={titulo} onChange={e => setTitulo(e.target.value)}/>
                    <input type="text" placeholder="Artista" 
                    value={artista} onChange={e => setArtista(e.target.value)}/>
                    <input type="text" placeholder="Album" 
                    value={album} onChange={e => setAlbum(e.target.value)}/>
                    <input type="text" placeholder="Ano" 
                    value={ano} onChange={e => setAno(e.target.value)}/>
                    <input type="text" placeholder="Genero" 
                    value={genero} onChange={e => setGenero(e.target.value)}/>
                    <button className="button" type="submit">Add music</button>
                </form>
                <section className="form">
                    {musicas !== undefined ? (
                    <div>
                        <strong>Músicas:</strong>
                        <ul>
                            {musicas.map(musica => (
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
                </section>
            </div>
        </div>
    )
}