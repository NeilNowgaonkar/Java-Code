public class Pattern_Matching_1
{
    public static void main(String args[])
    {
        char a[][]=new char[5][5];

        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                if(j<=i)
                {
                    a[i][j]='*';
                }
                else
                {
                    a[i][j]=' ';
                }
            }
        }

        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }
    }
}

