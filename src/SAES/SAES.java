package SAES;

import java.util.Scanner;

public class SAES
{
    static int[][] SBox ={
                        {1,0,0,1},{0,1,0,0},{1,0,1,0},{1,0,1,1},
                        {1,1,0,1},{0,0,0,1},{1,0,0,0},{0,1,0,1},
                        {0,1,1,0},{0,0,1,0},{0,0,0,0},{0,0,1,1},
                        {1,1,0,0},{1,1,1,0},{1,1,1,1},{0,1,1,1}
                    };

    static int[][] invSBox ={
                        {1,0,1,0},{0,1,0,1},{1,0,0,1},{1,0,1,1},
                        {0,0,0,1},{0,1,1,1},{1,0,0,0},{1,1,1,1},
                        {0,1,1,0},{0,0,0,0},{0,0,1,0},{0,0,1,1},
                        {1,1,0,0},{0,1,0,0},{1,1,0,1},{1,1,1,0}
                    };

    static int[][] mul_4 ={
                        {0,0,0,0},{0,1,0,0},{1,0,0,0},{1,1,0,0},
                        {0,0,1,1},{0,1,1,1},{1,0,1,1},{1,1,1,1},
                        {0,1,1,0},{0,0,1,0},{1,1,1,0},{1,0,1,0},
                        {0,1,0,1},{0,0,0,1},{1,1,0,1},{1,0,0,1}
                    };

    static int[][] mul_2 ={
                        {0,0,0,0},{0,0,1,0},{0,1,0,0},{0,1,1,0},
                        {1,0,0,0},{1,0,1,0},{1,1,0,0},{1,1,1,0},
                        {0,0,1,1},{0,0,0,1},{0,1,1,1},{0,1,0,1},
                        {1,0,1,1},{1,0,0,1},{1,1,1,1},{1,1,0,1}
                     };

    static int[][] mul_9 ={
                        {0,0,0,0},{1,0,0,1},{0,0,0,1},{1,0,0,0},
                        {0,0,1,0},{1,0,1,1},{0,0,1,1},{1,0,1,0},
                        {0,1,0,0},{1,1,0,1},{0,1,0,1},{1,1,0,0},
                        {0,1,1,0},{1,1,1,1},{0,1,1,1},{1,1,1,0}
                    };

    static int[][] mixcol ={
                        {1,4},
                        {4,1}
                    };

    int invmixcol[][]={
                        {9,2},
                        {2,9}
                    };

    static int[] sub_nib(int[] nib)
    {
        int index1=nib[0]*8+nib[1]*4+nib[2]*2+nib[3]*1;
        int index2=nib[4]*8+nib[5]*4+nib[6]*2+nib[7]*1;

        for(int i=0;i<4;i++)
            nib[i]=SBox[index1][i];

        for(int i=0;i<4;i++)
            nib[i+4]=SBox[index2][i];

        return nib;
    }

    static int [] rot_nib(int[] nib)
    {
        for(int j=0;j<4;j++)
        {
            int tmp=nib[0];
            for(int i=0;i<7;i++)
                nib[i]=nib[i+1];
            nib[7]=tmp;
        }
        return nib;
    }

    static void ExpandKey(int key[], int expandedKey[])
    {
        int w0[]=new int [8];
        int w1[]=new int [8];
        int w2[]=new int [8];
        int w3[]=new int [8];
        int w4[]=new int [8];
        int w5[]=new int [8];
        int tmp1[]={1,0,0,0,0,0,0,0};
        int tmp2[]={0,0,1,1,0,0,0,0};
        int temp[]=new int[8];

        //For w0
        for(int i=0;i<8;i++)
        {
            w0[i]=key[i];
        }

        //For w1
        for(int i=0;i<8;i++)
        {
            w1[i]=key[i+8];
        }

        //For w2
        for(int i=0;i<8;i++)
        {
            w2[i]=w0[i]^tmp1[i];
        }

        temp=sub_nib(rot_nib(w1));

        for(int i=0;i<8;i++)
        {
            w2[i] = w2[i] ^ temp[i];
        }

        //For w1
        for(int i=0;i<8;i++)
        {
            w1[i]=key[i+8];
        }


        //w3
        for(int i=0;i<8;i++)
        {
            w3[i]=w2[i]^w1[i];
        }


        //w4
        for(int i=0;i<8;i++)
        {
            w4[i] = w2[i] ^ tmp1[i];
        }

        temp=sub_nib(rot_nib(w3));

        for(int i=0;i<8;i++)
            w4[i]=w4[i]^temp[i];

        //w3
        for(int i=0;i<8;i++)
            w3[i]=w2[i]^w1[i];

        //w5
        for(int i=0;i<8;i++)
            w5[i]=w4[i]^w3[i];

        for(int i=0;i<48;i++)
        {
            if(i<8)
                expandedKey[i]=w0[i];
            else if(i<16 && i>7)
                expandedKey[i]=w1[i-8];
            else if(i<24 && i>15)
                expandedKey[i]=w2[i-16];
            else if(i<32 && i>23)
                expandedKey[i]=w3[i-24];
            else if(i<40 && i>31)
                expandedKey[i]=w4[i-32];
            else
                expandedKey[i]=w5[i-40];
        }
    }

    static void addRoundKey(int state[], int expandedKey[])
    {
        for(int i=0;i<16;i++)
        {
            state[i] ^= expandedKey[i];
        }
    }

    static void sub_bits_encrypt(int state[])
    {
        for(int i=0;i<15;i+=4)
        {
            int index=state[i]*8+state[i+1]*4+state[i+2]*2+state[i+3];
            for(int j=i;j<i+4;j++)
                state[j]=SBox[index][j%4];
        }
    }

    static void shiftrows(int state[])
    {
        int tmp[]=new int[8];
        tmp[0]=state[4];
        tmp[1]=state[5];
        tmp[2]=state[6];
        tmp[3]=state[7];

        tmp[4]=state[12];
        tmp[5]=state[13];
        tmp[6]=state[14];
        tmp[7]=state[15];

        for(int i=0;i<8;i++)
        {
            if(i>3)
                state[i]=tmp[i];
            else
                state[i+12]=tmp[i];
        }
    }

    static void mix_columns_encrypt(int state[])
    {
        int tmp[][]=new int[4][4],tmp1[][]=new int[4][4],i,j,k;

        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                tmp[i][j]=state[4*i+j];
                tmp1[i][j]=0;
            }
        }

        for(i=0;i<2;i++)
        {
            for(j=0;j<2;j++)
            {
                for(k=0;k<2;k++)
                {
                    if(mixcol[i][k]==1)
                    {
                        for(int x=0;x<4;x++)
                            tmp1[i+2*j][x] ^= mixcol[i][k]*tmp[k+2*j][x];
                    }
                    else if(mixcol[i][k]==4)
                    {
                        int index=tmp[k+2*j][0]*8+tmp[k+2*j][1]*4+tmp[k+2*j][2]*2+tmp[k+2*j][3];
                        for(int x=0;x<4;x++)
                            tmp1[i+2*j][x] ^=mul_4[index][x];

                    }
                }
            }
        }

        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
                state[4*i+j]=tmp1[i][j];
        }
    }


    static void Encrypt(int msg[], int expandedKey[], int encryptedmsg[])
    {
        int state[]=new int[16];
        for(int i=0;i<16;i++)
            state[i]=msg[i];

        addRoundKey(state,expandedKey);

        //Initial
        sub_bits_encrypt(state);
        shiftrows(state);
        mix_columns_encrypt(state);
        addRoundKey(state,expandedKey);

        //Final
        sub_bits_encrypt(state);
        shiftrows(state);
        addRoundKey(state,expandedKey);

        for (int i=0;i<16;i++)
            encryptedmsg[i]=state[i];
    }

    static void sub_bits_decrypt(int state[])
    {
        for(int i=0;i<15;i+=4)
        {
            int index=state[i]*8+state[i+1]*4+state[i+2]*2+state[i+3];
            for(int j=i;j<i+4;j++)
                state[j]=invSBox[index][j%4];
        }
    }

    static void mix_columns_decrypt(int state[])
    {
        int tmp[][]=new int[4][4];

        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                if(i==0)
                {
                    int index1=state[8*j+0]*8+state[8*j+1]*4+state[8*j+2]*2+state[8*j+3];
                    int index2=state[8*j+4]*8+state[8*j+5]*4+state[8*j+6]*2+state[8*j+7];
                    for(int k=0;k<4;k++)
                        tmp[2*j][k]=mul_9[index1][k]^mul_2[index2][k];
                }
                else
                {
                    int index2=state[8*j+0]*8+state[8*j+1]*4+state[8*j+2]*2+state[8*j+3];
                    int index1=state[8*j+4]*8+state[8*j+5]*4+state[8*j+6]*2+state[8*j+7];
                    for(int k=0;k<4;k++)
                        tmp[i+2*j][k]=mul_9[index1][k]^mul_2[index2][k];
                }
            }
        }

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
                state[4*i+j]=tmp[i][j];
        }
    }

    static void Decrypt(int encrypted_msg[], int expandedKey[], int decrypted_msg[])
    {
        int state[]=new int[16];
        for(int i=0;i<16;i++)
            state[i] =encrypted_msg[i];

        addRoundKey(state,expandedKey);

        //Initial
        shiftrows(state);
        sub_bits_decrypt(state);
        addRoundKey(state,expandedKey);

        //Final
        mix_columns_decrypt(state);
        sub_bits_decrypt(state);
        shiftrows(state);
        addRoundKey(state,expandedKey);

        for(int i=0;i<16;i++)
            decrypted_msg[i]=state[i];
    }




    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        int msg[]=new int[16], key[]=new int[16];
        int expandedKey[]=new int[48];
        int encrypted_msg[]=new int[16];
        int decrypted_msg[]=new int[16];

        System.out.print("\nEnter Message = ");
        for(int i=0;i<16;i++)
        {
            msg[i]=sc.nextInt();
        }

        System.out.print("\nEnter Key = ");
        for(int i=0;i<16;i++)
        {
            key[i]=sc.nextInt();
        }

        System.out.println("===============================================");

        ExpandKey(key,expandedKey);
        Encrypt(msg,expandedKey, encrypted_msg);

        System.out.print("\nEncrypted Message = ");
        for(int i=0;i<16;i++)
        {
            System.out.print(encrypted_msg[i]);
        }

        Decrypt(encrypted_msg,expandedKey,decrypted_msg);

        System.out.print("\n\nDecrypted Message = ");
        for(int i=0;i<16;i++)
        {
            System.out.print(decrypted_msg[i]);
        }

        System.out.println("\n\n===============================================");

    }
}
