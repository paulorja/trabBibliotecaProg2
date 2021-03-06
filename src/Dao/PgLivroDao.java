package Dao;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import Models.Livro;

public class PgLivroDao extends PgDaoFactory implements LivroDao {

	@Override
	public Livro inserir(Livro livro) {
		try {
			String sql=("INSERT INTO Livro VALUES("
			+ livro.getCodigo() + ",'" + livro.getTitulo()
			+ "','" + livro.getAutor()+"')");
			executeUpdate(sql);
		} catch (Exception e) {
            System.out.println(e);
        }
		return livro;
	}

	@Override
	public Livro consultaPorCod(int cod) {
	    try {
	        String sql=("SELECT * FROM Livro WHERE codigo='" + cod+"'");
	        ResultSet rs = executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columNumber = rsmd.getColumnCount();
	        String[] livroPropriedades= new String[columNumber];
	        while (rs.next()) {
	            for (int i=0 ; i<columNumber ; i++ ){
	                livroPropriedades[i]=rs.getString(i+1);
	            }
	        }
	        Livro p = new Livro(Integer.parseInt(livroPropriedades[0]),livroPropriedades[1],livroPropriedades[2].trim());
	        return p;
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return null;
	}

	@Override
	public void alterarPorCod(Livro livro) {
		try {
			String sql=("Update Livro set titulo='"
			+ livro.getTitulo() + "',autor='"+livro.getTitulo()+"' where codigo="+livro.getCodigo());
			executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	@Override
	public void removerPorCod(int cod) {
		try {
			String sql=("delete from Livro where codigo="+cod);
			executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public ArrayList<Livro> listaLivros() {
		ArrayList<Livro> livros = new ArrayList<Livro>();
		Livro l = new Livro();
		try {
			String sql=("SELECT * FROM Livro");
	        ResultSet rs = executeQuery(sql);
	        while (rs.next()) {
				l = new Livro();
				l.setCodigo(rs.getInt("codigo"));
				l.setTitulo(rs.getString("titulo"));
				l.setAutor(rs.getString("autor"));
				livros.add(l);
				l=null;
	        }
			return livros;
		} catch (Exception e) {
			System.out.println(e);
		}
		return livros;
	}

}
