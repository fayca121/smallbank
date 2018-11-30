package bz.faycal.smallbank.util;

public class PaginationUtil {

    private static final int MAX_PAGE_SIZE=5;

    public static int firstPage(int page,int totalPage){
        int lastPage=lastPage(page,totalPage);
        return (lastPage > MAX_PAGE_SIZE)?lastPage-MAX_PAGE_SIZE:0;
    }

    public static int lastPage(int page,int totalPage){
        return (page+MAX_PAGE_SIZE < totalPage)? page+MAX_PAGE_SIZE: totalPage-1;
    }

}
