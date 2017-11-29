package database.CrimeDbSchema;

/**
 * Created by 秦龙 on 2017/9/12.
 */

public class CrimeDbSchema {
    public static final class CrimeTable{
        public static final String NAME="crimes";

        public static final class Cols{
            public static final String UUID="uuid";
            public static final String TITLE="title";
            public static final String DATE="date";
            public static final String SOLVED="solved";
            public static final String SUSPECT="suspect";
        }
    }
}
