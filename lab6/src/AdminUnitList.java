import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> idToAdminUnit = new HashMap<>();
    Map<AdminUnit, Long> adminUnitToParentId = new HashMap<>();

    AdminUnitList(){}
    AdminUnitList(AdminUnitList x){
        this.units=x.units;
        this.idToAdminUnit=x.idToAdminUnit;
        this.adminUnitToParentId=x.adminUnitToParentId;
    }

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename,",",true);
        int i=0;
        //while(reader.next() && i<1000){
        while(reader.next()){
            AdminUnit m = new AdminUnit();
            Long parent;
            Long id;
            double x1,x2,x3,x4,y1,y2,y3,y4;


            if(reader.isMissing("id")){
                id=null;
            }
            else{
                id=reader.getLong("id");
            }

            if(reader.isMissing("parent")){
                parent=null;
            }
            else{
                parent=reader.getLong("parent");
            }

            if(reader.isMissing("name")){
                m.name="";
            }
            else{
                m.name = reader.get("name");
            }




            if(reader.isMissing("admin_level")){
                m.adminLevel = 0;
            }
            else{
                m.adminLevel = reader.getInt("admin_level");

            }


            if(reader.isMissing("population")){
                m.population=0;
            }
            else{
                m.population = reader.getDouble("population");
            }

            if(reader.isMissing("area")){
                m.area=0;
            }
            else{
                m.area = reader.getDouble("area");
            }

            if(reader.isMissing("density")){
                m.density=0;
            }
            else{
                m.density = reader.getDouble("density");
            }

            if(reader.isMissing("x1")){
                x1=0;
            }
            else{
                x1=reader.getDouble("x1");
            }

            if(reader.isMissing("x2")){
                x2=0;
            }
            else{
                x2=reader.getDouble("x2");
            }

            if(reader.isMissing("x3")){
                x3=0;
            }
            else{
                x3=reader.getDouble("x3");
            }

            if(reader.isMissing("x4")){
                x4=0;
            }
            else{
                x4=reader.getDouble("x4");
            }

            if(reader.isMissing("y1")){
                y1=0;
            }
            else{
                y1=reader.getDouble("y1");
            }

            if(reader.isMissing("y2")){
                y2=0;
            }
            else{
                y2=reader.getDouble("y2");
            }

            if(reader.isMissing("y3")){
                y3=0;
            }
            else{
                y3=reader.getDouble("y3");
            }

            if(reader.isMissing("y4")){
                y4=0;
            }
            else{
                y4=reader.getDouble("y4");
            }



            double xmax;
            double xmin;
            double ymax;
            double ymin;

            xmax=Math.max(Math.max(x1,x2),Math.max(x3,x4));
            ymax=Math.max(Math.max(y1,y2),Math.max(y3,y4));
            xmin=Math.min(Math.min(x1,x2),Math.min(x3,x4));
            ymin=Math.min(Math.min(y1,y2),Math.min(y3,y4));

            m.bbox= new BoundingBox(xmin,xmax,ymin,ymax);

            m.children= new ArrayList<AdminUnit>();
            m.parent=null;

            idToAdminUnit.put(id,m);
            adminUnitToParentId.put(m,parent);


            units.add(m);
            //System.out.printf()
            i++;

        }
        for(AdminUnit x: units){
            x.parent=idToAdminUnit.get(adminUnitToParentId.get(x));
            if(x.parent!=null){
                x.parent.children.add(x);
            }
        }
    }




    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     * @param out
     */
    void list(PrintStream out){
        for(AdminUnit x: units){
            out.println(x.toString(out));
        }
    }


    void fixMissingValues(AdminUnit au)
    {
        if(au!=null){
            if(au.population > 0 && au.density > 0)
            {
                return;
            }
            if(au.parent!=null) au.density = au.parent.density;
            au.population =au.area*au.density;
            fixMissingValues(au.parent);
        }

    }


    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     * @param out - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out,int offset, int limit ){
        for(int i=offset; i<=limit ; i++){
            out.println(units.get(i).toString(out));
        }

    }







    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     * @param pattern - wzorzec dla nazwy
     * @param regex - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit x: units){
            if(regex){
                if(x.name.matches(pattern)) ret.units.add(x);
            }
            else{
                if(x.name.contains(pattern)) ret.units.add(x);
            }
        }
        return ret;
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList m= new AdminUnitList();
        for(AdminUnit x: units){
            if(unit.adminLevel==x.adminLevel){
                if(unit.bbox.distanceTo(x.bbox)<=maxdistance){
                    m.units.add(x);
                }
            }
        }
        return m;
    }




    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByArea(){
        class AuComparatorByArea implements Comparator<AdminUnit> {


            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return Double.compare(o1.area,o2.area);
            }
        }
        AuComparatorByArea s= new AuComparatorByArea();
        units.sort(s);
        return this;
    }



    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByName(){
        class AuComparatorByName implements Comparator<AdminUnit> {


            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return o1.name.compareTo(o2.name);
            }
        }
        AuComparatorByName s= new AuComparatorByName();
        units.sort(s);
        return this;
    }








    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByPopulation(){
        units.sort((p,p1)->Double.compare(p.population,p1.population));
        return this;
    }




    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }


    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList list = new AdminUnitList(this);// Tworzy wyjściową listę
                                                        // Kopiuje wszystkie jednostki
        return list.sortInplace(cmp);                   // woła sortInPlace
    }



    /**
     *
     * @param pred referencja do interfejsu Predicate
     * @return nową listę, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */
    AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList filtr = new AdminUnitList();
        for(AdminUnit x: this.units){
            if(pred.test(x)){
                filtr.units.add(x);
            }
        }
        return filtr;
    }



}
