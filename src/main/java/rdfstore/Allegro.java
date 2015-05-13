package rdfstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;


public class Allegro {
    private static final String SERVER_URL = "http://200.219.214.215:10035";
    private static final String CATALOG_ID = "";
    private static final String REPOSITORY_ID = "qodra";
    private static final String USERNAME = "super";
    private static final String PASSWORD = "DAmgNj";

    private static List<RepositoryConnection> toClose = new ArrayList<RepositoryConnection>();
    private AGRepositoryConnection conn;




    public Allegro() throws Exception {

        AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);

        System.out.println("Server version: " + server.getVersion());
        //System.out.println("Server build date: " + server.getBuildDate());
        //System.out.println("Server revision: " + server.getRevision());
        //System.out.println("Available catalogs: " + server.listCatalogs());

        AGCatalog catalog = server.getCatalog(CATALOG_ID);          // open catalog
        AGRepository myRepository = catalog.openRepository(REPOSITORY_ID);
        myRepository.initialize();
        //System.out.println("Initialized repository.");
        //System.out.println("Repository is writable? " + myRepository.isWritable());
        AGRepositoryConnection conn = myRepository.getConnection();
        this.conn = conn;
        closeBeforeExit(this.conn);
        System.out.println("Successfully connected");
    }

    //adiciona um statement ao repositório
    public void addTriple(String s, String p, String o) throws RepositoryException{
        ValueFactory vf = conn.getRepository().getValueFactory();

        URI subject   = vf.createURI(s);
        URI predicate = vf.createURI(p);
        Literal object    = vf.createLiteral(o, XMLSchema.STRING);

        addStament(vf.createStatement(subject, predicate, object));
    }

    public void addStament(Statement stmt)throws RepositoryException{
        conn.add(stmt);
        conn.deleteDuplicates(null);
        System.out.println("New Statement added on qodra.");
    }

    public void addNt(String nt)throws RepositoryException{
        Scanner scanner = new Scanner(nt);
        scanner.useDelimiter("<");
        while (scanner.hasNext()){
            String s=null,p=null,o=null;

            if (scanner.hasNext()){
                s = scanner.next();
                s = s.replace(">", "");
            }
            if (scanner.hasNext()){
                p = scanner.next();
                p = p.replace(">", "");
            }
            if (scanner.hasNext()){
                o = scanner.next();
                o = o.replace(">", "");
            }
            addTriple(s, p, o);
        }
        scanner.close();
    }

    private void closeBeforeExit(RepositoryConnection conn) {
        toClose.add(conn);
    }

    private void close(RepositoryConnection conn) {
        try {
            conn.close();
            System.out.println("Reposytory closed");
        } catch (Exception e) {
            System.err.println("Error closing repository connection: " + e);
            e.printStackTrace();
        }
    }

    public void closeAll() {
        while (toClose.isEmpty() == false) {
            RepositoryConnection conn = toClose.get(0);
            close(conn);
            while (toClose.remove(conn)) {}
        }
    }
}
