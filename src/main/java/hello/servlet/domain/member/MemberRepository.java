package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 동시성 문제가 고려되어 있지 않음. 실무에선 ConcurrentHashMap, AtomicLong 사용 고려
* */
public class MemberRepository {
    //아래 부분을 싱글톤으로 제작했기 때문에 static이 없어도 무관
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //id값이 늘어남.

    //싱글톤으로 만들 예정
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {  //무조건 얘로 해야..
        return instance;
    }
    //싱글톤을 만들기 이전 private으로 생성자를 제한해야함.
    private MemberRepository() {
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id){
        return store.get(id);
    }
    public List<Member> findAll(){  //java.util
        return new ArrayList<>(store.values());
        //store에 있는 모든 값을 잡아서 ArrayList에 넘김.
        //ArrayList를 밖에서 값을 조작해도 store에 있는 value를 건들이고 싶지 않기 때문.(store 보호)
        //store에 있는 멤버를 직접 가져와 변경하면 값이 달라질 수 있음.
    }

    public void clearStore(){ //테스트 코드 전용
        store.clear();
    }

}
