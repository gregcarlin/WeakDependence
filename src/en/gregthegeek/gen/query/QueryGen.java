package en.gregthegeek.gen.query;

import static en.gregthegeek.gen.query.Constants.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import en.gregthegeek.util.BufferedWriter;

public class QueryGen {
    private static final String DIR   = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/queries/";
    private static final int    TESTS = 100;
    
    private static final HashMap<String,Query> queriesNoData   = new HashMap<String,Query>();
    private static final HashMap<String,Query> queriesEmpData  = new HashMap<String,Query>();
    private static final HashMap<String,Query> queriesPartData = new HashMap<String,Query>();
    
    public static void main(String[] args) throws IOException {
        
        Query add = new Query("%d + %d" , NUM_PAIRS);
        Query sub = new Query("%d - %d" , NUM_PAIRS);
        Query mul = new Query("%d * %d" , NUM_PAIRS);
        Query div = new Query("%d / %d" , NUM_PAIRS);
        Query mod = new Query("%d %% %d", NUM_PAIRS);
        Query neg = new Query("-%d"     , NUM_SINGS);
        
        n("add", add);
        n("sub", sub);
        n("mul", mul);
        n("div", div);
        n("mod", mod);
        n("neg", neg);
        
        Query and = new Query("%s and %s", LOGIC_PAIRS);
        Query or  = new Query("%s or %s" , LOGIC_PAIRS);
        Query not = new Query("not %s"   , LOGIC_SINGS);
        
        n("and", and);
        n("or", or);
        n("not", not);
        
        Query cat = new Query("%s + %s"  , STR_PAIRS);
        
        n("cat", cat);
        
        Query eqNum  = new Query("%d = %d" , NUM_PAIRS);
        Query eqStr  = new Query("%s = %s" , STR_PAIRS);
        Query neqNum = new Query("%d <> %d", NUM_PAIRS);
        Query neqStr = new Query("%s <> %s", STR_PAIRS);
        
        n("eqNum" , eqNum);
        n("eqStr" , eqStr);
        n("neqNum", neqNum);
        n("neqStr", neqStr);
        
        Query gr  = new Query("%d > %d" , NUM_PAIRS);
        Query ls  = new Query("%d < %d" , NUM_PAIRS);
        Query gre = new Query("%d >= %d", NUM_PAIRS);
        Query lse = new Query("%d <= %d", NUM_PAIRS);
        
        n("gr" , gr);
        n("ls" , ls);
        n("gre", gre);
        n("lse", lse);
        
        Query regexSing  = new Query("%s ~~ %s", STR_REGEX_SING);
        Query regexMult  = new Query("%s ~~ %s", STR_REGEX_MULT);
        Query nRegexSing = new Query("%s !~ %s", STR_REGEX_SING);
        Query nRegexMult = new Query("%s !~ %s", STR_REGEX_MULT);
        
        n("regexSing" , regexSing);
        n("regexMult" , regexMult);
        n("nRegexSing", nRegexSing);
        n("nRegexMult", nRegexMult);
        
        Query as      = new Query("bag(%s) as x"     , STRUCT_SINGS);
        Query groupas = new Query("bag(%s) groupas x", STRUCT_SINGS);
        
        n("as"     , as);
        n("groupas", groupas);
        
        Query now   = new Query("now()"); // check runtime based on time of day?
        Query dform = new Query("dateprec(1900-01-01, %s)", DATE_OPTS);
        
        n("now", now);
        n("dform", dform);
        
        Query sum = new Query("sum %s"  , STRUCT_SINGS);
        Query cnt = new Query("count %s", STRUCT_SINGS);
        Query min = new Query("min %s"  , STRUCT_SINGS);
        Query max = new Query("max %s"  , STRUCT_SINGS);
        Query avg = new Query("avg %s"  , STRUCT_SINGS);
        
        n("sum", sum);
        n("cnt", cnt);
        n("min", min);
        n("max", max);
        n("avg", avg);
        
        Query bag    = new Query("bag(%s)"              , STRUCT_SINGS);
        Query union  = new Query("bag(%s) union bag(%s)", STRUCT_PAIRS);
        Query struct = new Query("struct(%s)"           , STRUCT_SINGS);
        Query comma  = new Query("%s"                   , STRUCT_SINGS);
        
        n("bag"   , bag);
        n("union" , union);
        n("struct", struct);
        n("comma" , comma);
        
        Query subtract  = new Query("bag(%s) subtract bag(%s)" , STRUCT_PAIRS);
        Query in        = new Query("63 in bag(%s)"            , STRUCT_SINGS);
        Query contains  = new Query("bag(%s) contains 63"      , STRUCT_SINGS);
        Query intersect = new Query("bag(%s) intersect bag(%s)", STRUCT_PAIRS);
        Query unique    = new Query("unique(bag(%s))"          , STRUCT_SINGS);
        
        n("subtract" , subtract);
        n("in"       , in);
        n("contains" , contains);
        n("intersect", intersect);
        n("unique"   , unique);
        
        Query uniqueref = new Query("uniqueref(bag(%s))"   , EMP_STRUCT_SINGS);
        Query exists    = new Query("(bag(%s)).exists(sal)", EMP_STRUCT_SINGS);
        
        e("uniqueref", uniqueref);
        e("exists"   , exists);
        
        Query dot     = new Query("bag(%s).sal"                    , EMP_STRUCT_SINGS);
        Query join    = new Query("bag(Emp[1],Emp[2]) join bag(%s)", EMP_STRUCT_SINGS);
        Query where   = new Query("Emp where %s"                   , WHERE_ARGS);
        Query forall  = new Query("forall(bag(%s) as x) (x < 63)"  , STRUCT_SINGS);
        Query forsome = new Query("forsome(bag(%s) as x) (x < 63)" , STRUCT_SINGS);
        
        e("dot"    , dot);
        e("join"   , join);
        e("where"  , where);
        n("forall" , forall);
        n("forsome", forsome);
        
        Query orderbyNum  = new Query("bag(%s) orderby sal"     , EMP_STRUCT_SINGS);
        Query orderbyEnum = new Query("bag(%s) orderby position", EMP_STRUCT_SINGS);
        Query orderbyDate = new Query("bag(%s) orderby birthday", EMP_STRUCT_SINGS);
        Query orderbyStr  = new Query("bag(%s) orderby lname"   , EMP_STRUCT_SINGS);
        
        e("orderbyNum" , orderbyNum);
        e("orderbyEnum", orderbyEnum);
        e("orderbyDate", orderbyDate);
        e("orderbyStr" , orderbyStr);
        
        // test on depth/breadth?
        Query closeBy   = new Query("(bag(%s)) close by (component.leadsTo.Part)"       , PART_STRUCT_SINGS);
        Query leavesBy  = new Query("(bag(%s)) leaves by (component.leadsTo.Part)"      , PART_STRUCT_SINGS);
        Query closeUBy  = new Query("(bag(%s)) close unique by (component.leadsTo.Part" , PART_STRUCT_SINGS);
        Query leavesUBy = new Query("(bag(%s)) leaves unique by (component.leadsTo.Part", PART_STRUCT_SINGS);
        
        p("closeby"       , closeBy);
        p("leavesby"      , leavesBy);
        p("closeuniqueby" , closeUBy);
        p("leavesuniqueby", leavesUBy);
        
        Query ref   = new Query("ref(bag(%s))"  , EMP_STRUCT_SINGS);
        Query deref = new Query("deref(bag(%s))", EMP_STRUCT_SINGS);
        
        e("ref"  , ref);
        e("deref", deref);
        
        Query conditional = new Query("(Emp[1]).(if %s then \"a\" else \"b\")", WHERE_ARGS);
        
        e("condition", conditional);
        
        Query indexBag = new Query("(bag(%s))[1]", EMP_STRUCT_SINGS);
        Query indexNum = new Query("(bag(" + EMP_STRUCT_SINGS[EMP_STRUCT_SINGS.length-1][0] + "))[%d]", NUM_SINGS_P);
        Query rangeas  = new Query("bag(%s) rangeas x", EMP_STRUCT_SINGS);
        
        e("indexBag", indexBag);
        e("indexNum", indexNum);
        e("rangeas" , rangeas);
        
        write(queriesNoData);
        write(queriesEmpData , "WeakDependMod");
        write(queriesPartData, "PartMod");
        
        BufferedWriter all = new BufferedWriter(DIR + "all.txt");
        all.writelns("batch all-none.txt", "batch all-WeakDependMod.txt", "batch all-PartMod.txt");
        all.close();
    }
    
    private static final void write(HashMap<String,Query> queries) throws IOException {
        write(queries, null);
    }
    
    private static final void write(HashMap<String,Query> queries, String module) throws IOException {
        BufferedWriter bw = new BufferedWriter(DIR + "all-" + (module == null ? "none" : module) + ".txt");
        bw.writeln("$encoding = utf-8");
        bw.newLine();
        bw.writeln("cm ..");
        if(module != null) bw.writeln("cm " + module);
        bw.newLine();
        bw.writeln("set test plaintimes");
        bw.newLine();
        for(Entry<String,Query> e : queries.entrySet()) {
            String name = e.getKey();
            String fileName = name + ".txt";
            
            QueryWriter qw = new QueryWriter(DIR + fileName, TESTS, name);
            qw.write(e.getValue());
            qw.close();
            
            bw.write("batch " + fileName);
            bw.newLine();
        }
        bw.close();
    }
    
    private static final void n(String key, Query value) {
        queriesNoData.put(key, value);
    }
    
    private static final void e(String key, Query value) {
        queriesEmpData.put(key, value);
    }
    
    private static final void p(String key, Query value) {
        queriesPartData.put(key, value);
    }
}
