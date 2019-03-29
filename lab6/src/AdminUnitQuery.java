import java.util.Comparator;
import java.util.function.Predicate;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> p = a->true;
    Comparator<AdminUnit> cmp;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    /**
     * Ustawia listę jako przetwarzane źródło
     * @param src
     * @return this
     */
    AdminUnitQuery selectFrom(AdminUnitList src){
        this.src=src;
        return this;
    }

    /**
     *
     * @param pred - ustawia predykat p
     * @return this
     */
    AdminUnitQuery where(Predicate<AdminUnit> pred){
        p=pred;
        return this;
    }

    /**
     * Wykonuje operację p = p and pred
     * @param pred
     * @return this
     */
    AdminUnitQuery and(Predicate<AdminUnit> pred){
        p=p.and(p);
        return this;
    }
    /**
     * Wykonuje operację p = p or pred
     * @param pred
     * @return this
     */
    AdminUnitQuery or(Predicate<AdminUnit> pred){
        p=p.or(pred);
        return this;
    }

    /**
     * Ustawia komparator cmp
     * @param cmp
     * @return this
     */
    AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        this.cmp=cmp;
        return this;
    }

    /**
     * Ustawia limit
     * @param limit
     * @return this
     */
    AdminUnitQuery limit(int limit){
        this.limit=limit;
        return this;
    }
    /**
     * Ustawia offset
     * @param offset
     * @return this
     */
    AdminUnitQuery offset(int offset){
        this.offset=offset;
        return this;
    }

    /**
     * Wykonuje zapytanie i zwraca wynikową listę
     * @return przefiltrowana i opcjonalnie posortowana lista (uwzględniamy także offset/limit)
     */
    AdminUnitList execute(){
        AdminUnitList filtr = new AdminUnitList();
        for(AdminUnit x: src.units){
            if(p.test(x)){
                filtr.units.add(x);
            }
        }
        filtr.units.sort(cmp);
        AdminUnitList filtr2=new AdminUnitList();
        for(int i=offset; i<offset + limit; i++){
            if(i>=filtr.units.size()) break;
            filtr2.units.add(filtr.units.get(i));
        }
        return filtr2;
    }
}
