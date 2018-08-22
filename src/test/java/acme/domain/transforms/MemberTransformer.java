package acme.domain.transforms;

import acme.Member;
import cucumber.api.Transformer;

public class MemberTransformer extends Transformer<Member> {

    @Override
    public Member transform(String name) {
        return new Member(name);
    }
}
