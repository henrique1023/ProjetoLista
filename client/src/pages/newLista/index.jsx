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
            console.log(listName)
            const resp = await api.get(`api/lists/v1/${listName}`, { 
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }})

                setId(resp.data.id)
                setNome(resp.data.nome)
                setDescricao(resp.data.descricao)
        } catch (error) {
            alert('Error load book' + error)
        }
    }

    async function saveOrUpdate(e){
        e.preventDefault();

        const data = {
            nome,
            descricao,
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
        </div>
    )
}