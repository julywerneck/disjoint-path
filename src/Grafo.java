import java.util.*;

public class Grafo {
    public class Vertice {
        int nome;
        List<Aresta> adj;

        Vertice(int nome) {
            this.nome = nome;
            this.adj = new ArrayList<Aresta>();
        }

        void addAdj(Aresta e) {
            adj.add(e);
        }
    }

    public class Aresta {
        Vertice origem;
        Vertice destino;
        int fluxo;

        Aresta(Vertice origem, Vertice destino, int fluxo) {
            this.origem = origem;
            this.destino = destino;
            this.fluxo = fluxo;
        }
    }

    public class Disjointpath {
        List<Aresta> path;
        int size;
        Vertice s;
        Vertice t;

        Disjointpath(Vertice origem, Vertice destino) {
            this.s = origem;
            this.t = destino;
            this.path = new ArrayList<Aresta>();
            this.size = 0;
        }

        void addPath(Aresta e) {
            path.add(e);
        }

        int getSize() {
            return this.size;
        }

        void setSize(int add) {
            this.size = this.size + add;
        }

        void findDisjointPaths() {
            Stack<Aresta> stack = new Stack<Aresta>();
            Stack<Aresta> temp = new Stack<Aresta>();
            int origem = 0;
            stack.push(s.adj.get(origem));
            while (!stack.isEmpty()) {
                Aresta edge = stack.pop();
                Vertice current = edge.destino;
                edge.fluxo = 0;
                temp.push(edge);
                for (Aresta e : current.adj) {
                    if (e.fluxo > 0) {
                        if (e.destino.nome == 5) {
                            temp.push(e);
                            setSize(1);
                            for (Aresta i : temp) {
                                addPath(i);
                            }
                            temp.clear();
                            stack.clear();
                            e.fluxo = 0;
                            origem += 1;
                            if (origem < s.adj.size()) {
                                stack.push(s.adj.get(origem));
                            }
                        } else {
                            stack.push(e);
                        }
                    }
                }
            }
        }

        void printResult() {
            String r = "";
            System.out.println("O total de caminhos disjuntos possiveis e: " + this.size);
            for (Aresta e : this.path) {
                Vertice v = e.destino;
                Vertice o = e.origem;
                r += o.nome + " -> ";
                r += v.nome + ": ";
                r += e.fluxo + "\n";
                if (v.nome == 5) {
                    r += "--------------\n";
                }
            }
            System.out.print(r);
        }
    }

    List<Vertice> vertices;
    List<Aresta> arestas;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    Vertice addVertice(int nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    Aresta addAresta(Vertice origem, Vertice destino, int fluxo) {
        Aresta e = new Aresta(origem, destino, fluxo);
        origem.addAdj(e);
        arestas.add(e);
        return e;
    }

    Disjointpath disjointPath(Vertice origem, Vertice destino) {
        Disjointpath dp = new Disjointpath(origem, destino);
        return dp;
    }

    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                Vertice s = e.origem;
                r += s.nome + " -> ";
                r += v.nome + ": ";
                r += e.fluxo + "\n";
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Grafo g = new Grafo();
        Vertice s = g.addVertice(0);
        Vertice t = g.addVertice(5);
        Vertice one = g.addVertice(1);
        Vertice two = g.addVertice(2);
        Vertice three = g.addVertice(3);
        Vertice four = g.addVertice(4);
        Aresta so = g.addAresta(s, one, 1);
        Aresta st = g.addAresta(s, three, 1);
        Aresta sf = g.addAresta(s, four, 1);
        Aresta ot = g.addAresta(one, two, 1);
        Aresta oth = g.addAresta(one, three, 1);
        Aresta of = g.addAresta(one, four, 1);
        Aresta tt = g.addAresta(two, t, 1);
        Aresta thtwo = g.addAresta(three, two, 1);
        Aresta tht = g.addAresta(three, t, 1);
        Aresta ftwo = g.addAresta(four, two, 1);
        Aresta ft = g.addAresta(four, t, 1);
        System.out.println(g);
        Disjointpath dp = g.disjointPath(s, t);
        dp.findDisjointPaths();
        dp.printResult();
    }
}
