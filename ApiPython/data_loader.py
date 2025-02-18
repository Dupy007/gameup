# data_loader.py
import pandas as pd
from sqlalchemy import create_engine

def load_training_data_from_mysql():
    username = "gameup"
    password = "gameup"
    host = "localhost"
    port = "33061"
    database = "gameup"

    connection_string = f"mysql+pymysql://{username}:{password}@{host}:{port}/{database}?charset=utf8mb4"

    engine = create_engine(connection_string)

    query = "select pl.id ,g.nom  game_name,g.id game_id,u.username,u.id user_id,g.prix prix,pl.quantite from purchase_line pl join purchase p on p.id = pl.purchase_id join game g on pl.game_id = g.id join user u on u.id = p.client_id"

    data = pd.read_sql(query, engine)
    return data
