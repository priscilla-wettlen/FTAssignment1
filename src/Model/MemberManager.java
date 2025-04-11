package Model;

import java.util.ArrayList;
import java.util.List;

public class MemberManager {
    private List<Member> members;  //you may use other types of collections
    private int memberID; //Optional start id

    public MemberManager() {
        members = new ArrayList<>();
    }

    private boolean checkIndex(int index) {
        return (index >= 0) && (index < members.size());
    }

    public Member get(int index)
    {
        if (checkIndex(index))
            return members.get(index);
        else
            return null;
    }

    public void add(Member member)
    {
        members.add(member);
    }

    public void remove(int index)
    {
        if (checkIndex(index))
            members.remove(index);
    }

    public int size()
    {
        return members.size();
    }

    public  String[] getMemberInfoStrings()
    {
        if( (members == null) || (members.size() <= 0) )
            return new String []{"Members: ", " "};

        //Create an array with a size equal to the number of items in the list.
        //one extra lines for the title, size and a blank line.
        String [] infoStrings = new String[members.size()+3]; //See (x)

        infoStrings[0] =   String.format("Number of members: %s", members.size()); //(1)
        infoStrings[1] = "";  //(2)
        int j= 2;

        //Fill the list with info on each element, using the object's toString
        //method.
        for (int i=0; i < members.size(); i++)
            infoStrings[j++] = members.get(i).toString();

        infoStrings[infoStrings.length-1] = "";  //(3)
        return infoStrings;
    }

    public void addTestMember() {
        for (int i = 0; i < 15; i++) {
            addNewTestMember();
        }
    }

    public boolean noMembersLeft()
    {
        return (members == null) || (members.isEmpty());
    }

    public Member addNewTestMember()
    {
        Member member = new Member(0, "Member"); // Assume Product constructor and methods are defined elsewhere
        member.setId(memberID);
        member.setName("Member" + (memberID++));
        members.add(member);
        return member;
    }

}